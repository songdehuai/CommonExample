package com.songdehuai.commonlib.utils;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

	private List<Observer> obsrevers = new ArrayList<Observer>();
	public T value;
	
	public Observable(T value) {
		super();
		this.value = value;
	}

	public List<Observer> getObsrevers() {
		return obsrevers;
	}

	public void setObsrevers(List<Observer> obsrevers) {
		this.obsrevers = obsrevers;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		for (int i=0; i<this.obsrevers.size(); i++){
			this.obsrevers.get(i).notice.notice(this.value);	
		}
	}

	
	public void addNotice(Object target, Notice notice, boolean igoreInit){
		this.obsrevers.add(new Observer(target, notice));
		if (igoreInit) {  notice.notice(this.getValue()); }
	}
	
	public void addNotice(Object target, Notice notice){
		this.obsrevers.add(new Observer(target, notice));
	}
	
	

}
