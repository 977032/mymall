package com.ffyc.server.action.manager;

import com.ffyc.server.model.Manager;

public class ManagerUpdateAction extends BaseAction {

	private static final long serialVersionUID = -3693542230977332521L;
	private String mgid;
	private Manager manager;

	public String execute() throws Exception {
		this.manager = this.managerService.findById(this.mgid);
		return "success";
	}

	public String update() throws Exception {
		Manager um = this.managerService.findById(this.manager.getId());
		if ((this.manager.getPassword() != null)
				&& (!this.manager.getPassword().equals(""))) {
			um.setPassword(this.manager.getPassword());
		}
		um.setStatus(this.manager.getStatus());
		um.setEmail(this.manager.getEmail());
		um.setName(this.manager.getName());
		um.setCtype(this.manager.getCtype());
		um.setNickname(this.manager.getNickname());
		um.setIdcard(this.manager.getIdcard());
		this.managerService.update(um);
		return "success";
	}

	public String getMgid() {
		return this.mgid;
	}

	public void setMgid(String mgid) {
		this.mgid = mgid;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
