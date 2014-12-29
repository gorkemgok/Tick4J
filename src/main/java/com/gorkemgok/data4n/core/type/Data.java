package com.gorkemgok.data4n.core.type;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data<T> {
	private T t;
	public Data(T t){
		this.t = t;
	}
	public T get() {
		return t;
	}
	public void set(T t) {
		this.t = t;
	}
	
	public String toString(){
		if (t instanceof Date){
			return new SimpleDateFormat("dd/MM/yyyy EEE").format(t);
		}
		return t.toString();
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object d1){
		if (d1!=null && d1 instanceof Data && ((Data) d1).get().equals(this.get())){
			return true;
		}
		return false;
	}
}
