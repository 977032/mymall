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
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.UserMapper;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.User;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.service.ManagerService;
import com.ffyc.server.service.UserService;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.vo.UserTokenVO;
import com.opensymphony.xwork2.ActionContext;

@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl implements UserService,ManagerService
{
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private ManagerMapper managerMapper;
    
    @Override
    public LoginVO getUserByMobile(String mobile){
    	LoginVO loginVO= null;
    	Manager dc = new Manager();
    	dc.setMobile(mobile);
    	dc.setStatus(Manager.STATUS_NORMAL);
    	List<Manager> list = managerMapper.findByManager(dc, null, null);
    	if(list.size()>0){
    		Manager manager = list.get(0);
    		loginVO = new LoginVO();
    		loginVO.setId(manager.getId());
    		loginVO.setAccount(manager.getAccount());
    		loginVO.setName(manager.getName());
    		loginVO.setMobile(manager.getMobile());
    		loginVO.setType(Manager.MANAGER_TYPE);
    	}
    	return loginVO;
    }

    @Override
    public void modifyUserPhoto(String id, MultipartFile file) throws AttachIOException 
    {
        try
        {
            File dir = new File(AttachDir.USER_MOBILE_DIR, id);
            AttachUtil.deleteStartsWith(dir, "$PHOTO.");
            dir.mkdirs();
            file.transferTo(new File(dir, "$PHOTO." + FilenameUtils.getExtension(file.getOriginalFilename())));
        }
        catch(IOException e)
        {
            throw new AttachIOException("处理用户头像时出错:" + e.getMessage());
        }
    }

    @Override
    public void downloadPhoto(String id, HttpServletResponse response, OutputStream out) throws FileNotFoundException, AttachIOException
    {
        File file = null;
        // 获取附件
        {
            File files[] = new File(AttachDir.USER_MOBILE_DIR, id + "/").listFiles(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    if(name.startsWith("$PHOTO."))
                    {
                        return true;
                    }
                    return false;
                }
            });
            if(files != null && files.length > 0)
            {
                file = files[0];
            }
        }

        if(file == null)
        {
            throw new FileNotFoundException("找不到文件");
        }

        try
        {
            String disposition = "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8");
            response.setContentType(MIMEUtils.getMIME(file.getName()));
            response.addHeader("Content-Disposition", disposition);
            response.addHeader("Content-Length", String.valueOf(file.length()));

            FileInputStream in = new FileInputStream(file);

            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(out);

            byte[] buffer = new byte[1024];

            while(bis.read(buffer) != -1)
            {
                bos.write(buffer);
            }

            bos.flush();
            bos.close();
            out.flush();
            out.close();
            bis.close();
            in.close();

        }
        catch(IOException e)
        {
            throw new AttachIOException("头像下载失败，" + e.getMessage());
        }
    }

	@Override
	public void updateUserBasicInfo(String id, String gender, String name) throws DuplicateUsernameException {
		String userId = null;
		Manager dc = new Manager();
		dc.setName(name);
		List<Manager> list = managerMapper.findByManager(dc, null, null);
		if(list.size()>0){
			Manager manager = list.get(0);
			userId = manager.getId();
		}
		if( userId!= null && userId!=id)
		{
			throw new DuplicateUsernameException("该名称已被使用！");
		}
		Manager manager = managerMapper.findById(id);
		if(manager!=null){
			manager.setGender(gender);
			manager.setName(name);
			this.managerMapper.update(manager);
		}
	}
	
	@Override
	public void updateUserLocation(String userId,double lng,double lat){
		this.userMapper.updateUserLocation(userId, lng, lat);
	}

	
	@Override
    public UserTokenVO getUserTokenByToken(String token){
    	return this.userMapper.getUserTokenByToken(token);
    }
	@Override
    public UserTokenVO getUserTokenByUserId(String userId){
    	return this.userMapper.getUserTokenByUserId(userId);
    }
	@Override
    public void insertUserToken(UserTokenVO userTokenVO){
    	this.userMapper.insertUserToken(userTokenVO);
    }
	@Override
    public void updateUserToken(String userId,String token){
    	this.userMapper.updateUserToken(userId, token);
    }

	@Override
	public void deleteUserToken(String userId) {
		this.userMapper.deleteUserToken(userId);
	}

	@Override
	public User getUserByUserId(String userId) {
		return this.managerMapper.findById(userId);
	}

	@Override
	public List<UserBasicInfo> searchUserByName(String name, int page) {
    	PageCalc pageCalc = new PageCalc(managerMapper.searchManagerByNameCount(name));
        List<UserBasicInfo> list = managerMapper.searchManagerByName(name, pageCalc.getStart(page), pageCalc.getPageSize());
        return list;
	}

	@Override
	public int searchUserByNameCount(String name) {
		return this.managerMapper.searchManagerByNameCount(name);
	}

	@Override
	public Notice login(String account, String password, String validateCode) {
		 Notice notice = new Notice();
		    String verifyCode = (String)ActionContext.getContext().getSession().get("VerifyCode");
		    //modify 测试用暂时屏蔽
		    validateCode = verifyCode;
		    if ((verifyCode != null) && (verifyCode.equals(validateCode)))
		    {
		      Manager manager = this.managerMapper.findByAccount(account);
		      if (manager != null)
		      {
		        if (MD5Utils.getMD5Str(password).equals(manager.getPassword()))
		        {
		          if (!manager.getStatus().equals("noactived"))
		          {
		            this.session.put("manager", manager);
		            notice.setStatus(true);
		            notice.setCode("login success");
		            notice.setTitle("提示");
		            notice.setBody("登陆成功");
		            

		            manager.setLogincount(Integer.valueOf(manager.getLogincount().intValue() + 1));
		            manager.setLastlogin(getTimestamp()); 
		            this.managerMapper.update( manager);
		          }
		          else
		          {
		            notice.setStatus(false);
		            notice.setCode("account not actived");
		            notice.setTitle("错误");
		            notice.setBody("该账号未激活");
		          }
		        }
		        else
		        {
		          notice.setStatus(false);
		          notice.setCode("account or password error");
		          notice.setTitle("错误");
		          notice.setBody("您的用户名或者密码输入错误，请重试");
		        }
		      }
		      else
		      {
		        notice.setStatus(false);
		        notice.setCode("account not exist");
		        notice.setTitle("错误");
		        notice.setBody("该账号不存在");
		      }
		    }
		    else
		    {
		      notice.setStatus(false);
		      notice.setCode("verifyCode error");
		      notice.setTitle("错误");
		      notice.setBody("验证码错误");
		    }
		    return notice;
	}

	@Override
	public void save(Manager manager) {
		this.managerMapper.save(manager);
	}
	
	@Override
	public void update(Manager manager) {
		this.managerMapper.update(manager);
	}

	@Override
	public void delete(String id) {
		Manager manager = this.managerMapper.findById(id);
		manager.setStatus(Manager.STATUS_DELETE);
		this.managerMapper.update(manager);
	}

	@Override
	public Manager findById(String id) {
		return managerMapper.findById(id);
	}

	@Override
	public List<Manager> findAll() {
		return managerMapper.findAll();
	}

	@Override
	public Manager findByAccount(String account) {
		return managerMapper.findByAccount(account);
	}

	@Override
	public List<Manager> findByManager(Manager dc) {
		return managerMapper.findByManager(dc, null, null);
	}

	@Override
	public List<Manager> findByManager(Manager dc,Integer startIndex, Integer pageSize) {
		return managerMapper.findByManager(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByManager(Manager dc) {
		return managerMapper.getCountByManager(dc);
	}

	@Override
	public PaginationSupport findPageByManager(Manager dc, Integer page,
			Integer pageSize) {
		if (page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize;
		int totalCount = this.managerMapper.getCountByManager(dc);
		List list = this.managerMapper
				.findByManager(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,
				startIndex, pageSize);
		return ps;
	}
	
}
