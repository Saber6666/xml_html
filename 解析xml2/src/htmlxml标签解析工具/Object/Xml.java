package htmlxml标签解析工具.Object;

import java.util.HashMap;

public class Xml {
	private String 名称,内容="",实体内容="",注释,无结束标记的标签;//要初始化“内容”。否则就会造成空指针异常
	public boolean is为实体内容=true;
	private HashMap<String,String> 属性属性值=new HashMap<String, String>();
	public Xml() {}
	public String get名称() {
		return 名称;
	}
	public void set名称(String 名称) {
		this.名称 = 名称;
	}
	public String get内容() {
		return 内容;
	}
	public void set内容(String 内容) {
		this.内容 = 内容;
	}
	public HashMap<String,String> get属性属性值() {
		return 属性属性值;
	}
	public void set属性属性值(HashMap<String,String> 属性属性值) {
		this.属性属性值 = 属性属性值;
	}
	public String get注释() {
		return 注释;
	}
	public void set注释(String 注释) {
		this.注释 = 注释;
	}
	public String get无结束标记的标签() {
		return 无结束标记的标签;
	}
	public void set无结束标记的标签(String 无结束标记的标签) {
		this.无结束标记的标签 = 无结束标记的标签;
	}
	public String get实体内容() {
		return 实体内容;
	}
	public void set实体内容(String 实体内容) {
		this.实体内容 = 实体内容;
	}

	@Override
	public String toString() {
		return "Xml{" +
				"名称='" + 名称 + '\'' +
				", 内容='" + 内容 + '\'' +
				", 实体内容='" + 实体内容 + '\'' +
				", 注释='" + 注释 + '\'' +
				", 无结束标记的标签='" + 无结束标记的标签 + '\'' +
				", is为实体内容=" + is为实体内容 +
				", 属性属性值=" + 属性属性值 +
				'}';
	}
}
