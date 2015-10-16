package com.baosteel.qcsh.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 常用发票
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-17
 */
public class CommonTicket implements Serializable{
	
	/**发票ID**/
	private String memberTicketId;
	
	/**抬头公司**/
	private String ticketDescription;

	public CommonTicket(JSONObject obj){
		memberTicketId = obj.optString("memberTicketId");
		ticketDescription = obj.optString("ticketDescription");
	}
	
	public CommonTicket(String company){
		this.ticketDescription = company;
	}
	
	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String company) {
		this.ticketDescription = company;
	}

	public String getMemberTicketId() {
		return memberTicketId;
	}

	public void setMemberTicketId(String memberTicketId) {
		this.memberTicketId = memberTicketId;
	}
	
}
