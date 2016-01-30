package com.ffyc.server.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ffyc.exception.attach.AttachIOException;
import com.ffyc.exception.permission.DuplicateUsernameException;
import com.ffyc.server.common.AttachDir;
import com.ffyc.server.common.PageCalc;
import com.ffyc.server.common.utils.AttachUtil;
import com.ffyc.server.common.utils.MD5Utils;
import com.ffyc.server.common.utils.MIMEUtils;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.UserMapper;
import com.ffyc.server.model.Bank;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Point;
import com.ffyc.server.model.User;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.service.BankService;
import com.ffyc.server.service.MemberService;
import com.ffyc.server.service.PointService;
import com.ffyc.server.service.UserService;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.vo.UserTokenVO;
import com.opensymphony.xwork2.ActionContext;

@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl implements UserService,
		MemberService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private BankService bankService;

	@Autowired
	private PointService pointService;

	@Override
	public LoginVO getUserByMobile(String mobile) {
		LoginVO loginVO = null;
		Member dc = new Member();
		dc.setStatus(Member.STATUS_NORMAL);
		dc.setMobile(mobile);
		List<Member> list = memberMapper.findByMember(dc, null, null);
		if (list.size() > 0) {
			Member member = list.get(0);
			loginVO = new LoginVO();
			loginVO.setId(member.getId());
			loginVO.setAccount(member.getAccount());
			loginVO.setName(member.getName());
			loginVO.setMobile(member.getMobile());
			loginVO.setType(Member.MEMBER_TYPE);
		}
		return loginVO;
	}

	@Override
	public void modifyUserPhoto(String id, MultipartFile file)
			throws AttachIOException {

		try {
			File dir = new File(AttachDir.USER_MOBILE_DIR, id);
			AttachUtil.deleteStartsWith(dir, "$PHOTO.");
			dir.mkdirs();
			file.transferTo(new File(dir, "$PHOTO."
					+ FilenameUtils.getExtension(file.getOriginalFilename())));
		} catch (IOException e) {
			throw new AttachIOException("处理用户头像时出错:" + e.getMessage());
		}
	}

	@Override
	public void downloadPhoto(String id, HttpServletResponse response,
			OutputStream out) throws FileNotFoundException, AttachIOException {
		File file = null;
		// 获取附件
		{
			File files[] = new File(AttachDir.USER_MOBILE_DIR, id + "/")
					.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							if (name.startsWith("$PHOTO.")) {
								return true;
							}
							return false;
						}
					});
			if (files != null && files.length > 0) {
				file = files[0];
			}
		}

		if (file == null) {
			throw new FileNotFoundException("找不到文件");
		}

		try {
			String disposition = "attachment;filename="
					+ URLEncoder.encode(file.getName(), "UTF-8");
			response.setContentType(MIMEUtils.getMIME(file.getName()));
			response.addHeader("Content-Disposition", disposition);
			response.addHeader("Content-Length", String.valueOf(file.length()));

			FileInputStream in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(out);

			byte[] buffer = new byte[1024];

			while (bis.read(buffer) != -1) {
				bos.write(buffer);
			}
			bos.flush();
			bos.close();
			out.flush();
			out.close();
			bis.close();
			in.close();
		} catch (IOException e) {
			throw new AttachIOException("头像下载失败，" + e.getMessage());
		}
	}

	@Override
	public void updateUserBasicInfo(String id, String gender, String name)
			throws DuplicateUsernameException {
		Member member = memberMapper.findById(id);
		member.setName(name);
		member.setGender(gender);
		this.memberMapper.update(member);
		;
	}

	@Override
	public void updateUserLocation(String userId, double lng, double lat) {
		this.userMapper.updateUserLocation(userId, lng, lat);
	}

	@Override
	public UserTokenVO getUserTokenByToken(String token) {
		return this.userMapper.getUserTokenByToken(token);
	}

	@Override
	public UserTokenVO getUserTokenByUserId(String userId) {
		return this.userMapper.getUserTokenByUserId(userId);
	}

	@Override
	public void insertUserToken(UserTokenVO userTokenVO) {
		this.userMapper.insertUserToken(userTokenVO);
	}

	@Override
	public void updateUserToken(String userId, String token) {
		this.userMapper.updateUserToken(userId, token);
	}

	@Override
	public void deleteUserToken(String userId) {
		this.userMapper.deleteUserToken(userId);
	}

	@Override
	public User getUserByUserId(String userId) {
		return this.memberMapper.findById(userId);
	}

	@Override
	public List<UserBasicInfo> searchUserByName(String name, int page) {
		PageCalc pageCalc = new PageCalc(
				memberMapper.searchMemberByNameCount(name));
		List<UserBasicInfo> list = memberMapper.searchMemberByName(name,
				pageCalc.getStart(page), pageCalc.getPageSize());
		return list;
	}

	@Override
	public int searchUserByNameCount(String name) {
		return this.memberMapper.searchMemberByNameCount(name);
	}

	@Override
	public void save(Member member) {
		this.memberMapper.save(member);
	}

	@Override
	public void update(Member member) {
		this.memberMapper.update(member);
	}

	@Override
	public void delete(String id) {
		Member member = this.memberMapper.findById(id);
		member.setStatus(Member.STATUS_DELETE);
		this.memberMapper.update(member);
	}

	@Override
	public Member findById(String id) {
		return this.memberMapper.findById(id);
	}

	@Override
	public List<Member> findAll() {
		return this.memberMapper.findAll();
	}

	@Override
	public List<Member> findByMember(Member dc) {
		return this.memberMapper.findByMember(dc, null, null);
	}

	@Override
	public List<Member> findByMember(Member dc, Integer start, Integer count) {
		return this.memberMapper.findByMember(dc, start, count);
	}

	@Override
	public int getCountByMember(Member dc) {
		return this.memberMapper.getCountByMember(dc);
	}

	@Override
	public PaginationSupport findPageByMember(Member dc, Integer page,
			Integer pageSize) {
		if (page <= 0)
			page = 1;
		int startIndex = (page - 1) * pageSize;
		int totalCount = this.memberMapper.getCountByMember(dc);
		List list = this.memberMapper.findByMember(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,
				startIndex, pageSize);
		return ps;
	}

	@Override
	public Notice register(Member member, String validateCode) {
		Notice notice = new Notice();
		String verifyCode = (String) ActionContext.getContext().getSession()
				.get("VerifyCode");
		if ((verifyCode != null) && (verifyCode.equals(validateCode))) {
			String account = member.getAccount();
			Member m = this.memberMapper.findByAccount(account);
			if ((m != null) && (!m.getAccount().equals(""))) {
				notice.setStatus(false);
				notice.setCode("user exist");
				notice.setTitle("提示");
				notice.setBody("该账号已被使用");
				return notice;
			}
			member.setId(getUuid());
			member.setPassword(MD5Utils.getMD5Str(member.getCpassword()));
			member.setActivation(member.getId());
			member.setStatus(Member.STATUS_NORMAL);
			member.setCtype(Member.CTYPE_NORMAL);
			member.setLogincount(Integer.valueOf(0));
			member.setCtime(getTimestamp());
			this.memberMapper.save(member);

			Bank bank = this.bankService.openAccount();
			member.setBank(bank.getId());
			member.setBankk(bank);
			Point point = this.pointService.openAccount();
			member.setPointt(point);
			member.setPoint(point.getId());
			this.memberMapper.update(member);

			notice.setStatus(true);
			notice.setCode("register success");
			notice.setTitle("提示");
			notice.setBody("注册成功");
		} else {
			notice.setStatus(false);
			notice.setCode("validateCode error");
			notice.setTitle("错误");
			notice.setBody("验证码错误");
			return notice;
		}
		return notice;
	}

	@Override
	public Notice login(String account, String password, String validateCode) {
		Notice notice = new Notice();
		String verifyCode = (String) ActionContext.getContext().getSession()
				.get("VerifyCode");
		// modify modify for test
		verifyCode = validateCode;
		if ((verifyCode != null) && (verifyCode.equals(validateCode))) {
			Member member = this.memberMapper.findByAccount(account);
			if (member != null) {
				if (MD5Utils.getMD5Str(password).equals(member.getPassword())) {
					if (!member.getStatus().equals("notactived")) {
						this.session.put("member", member);
						notice.setStatus(true);
						notice.setCode("login success");
						notice.setTitle("提示");
						notice.setBody("登陆成功");

						member.setLogincount(Integer.valueOf(member
								.getLogincount().intValue() + 1));
						member.setLastlogin(getTimestamp());
						this.memberMapper.update(member);
					} else {
						notice.setStatus(false);
						notice.setCode("account not actived");
						notice.setTitle("错误");
						notice.setBody("该账号未激活");
					}
				} else {
					notice.setStatus(false);
					notice.setCode("account or password error");
					notice.setTitle("错误");
					notice.setBody("您的用户名或者密码输入错误，请重试");
				}
			} else {
				notice.setStatus(false);
				notice.setCode("account not exist");
				notice.setTitle("错误");
				notice.setBody("该账号不存在");
			}
		} else {
			notice.setStatus(false);
			notice.setCode("verifyCode error");
			notice.setTitle("错误");
			notice.setBody("验证码错误");
		}
		return notice;
	}

	@Override
	public Member findByAccount(String account) {
		return memberMapper.findByAccount(account);
	}

}
