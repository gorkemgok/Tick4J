package com.gorkemgok.data4n.core.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

	public boolean equals(Object d1){
		Object o1 = ((Data) d1).get();
		T o2 = this.get();
		if (d1!=null && d1 instanceof Data && ((Data) d1).get().equals(this.get())){
			return true;
		}
		return false;
	}
}
