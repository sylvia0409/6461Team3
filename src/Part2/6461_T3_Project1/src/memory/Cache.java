/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.LinkedList;
import util.Const;

/**
 *
 * @author yiqian
 */
public class Cache {

        static public class CacheLine {

		int tag;
		int data;

		public CacheLine(int tag, int data) {
			this.tag = tag;
			this.data = data;
		}

		public int getTag() {
			return this.tag;
		}

		public void setTag(int tag) {
			this.tag = tag;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
	}

	LinkedList<CacheLine> cacheLines;

	public Cache() {
		this.cacheLines = new LinkedList<CacheLine>();
	}

	public LinkedList<CacheLine> getCacheLines() {
		return cacheLines;
	}

	public void add(int address, int value) {
		if (this.cacheLines.size() >= Const.CACHE_LINES) {
			this.cacheLines.removeLast();
		}
		this.cacheLines.addFirst(new CacheLine(address, value));
	}
}
