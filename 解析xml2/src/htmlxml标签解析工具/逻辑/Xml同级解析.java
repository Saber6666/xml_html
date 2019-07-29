package htmlxml标签解析工具.逻辑;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import htmlxml标签解析工具.Object.Xml;

public class Xml同级解析 {
    private int 标签开始名称;
    private boolean is结束标记 = true;

    public Xml同级解析() {
    }

    public List<Xml> 解析HTML(String 标签或html) {
        List<Xml> list = new ArrayList<Xml>();
        char[] w文字 = 标签或html.toCharArray();
//这个for循环可能出现错误，原因可能i的数值超过int的范围，可能要改为long
        for (int i = 0; i < w文字.length; i++) {
            Xml ht = new Xml();
            if (w文字[i] == '<') {
                if (w文字[i+1] == '!') {
                    i = 删除注释(w文字, i);
                } else {
                    标签开始名称 = i;//在这里开始记录标签的开始名称
                    while (i < 标签或html.length()) {
                        i++;
						/*如果不是注释，那么判断“<”的下一个字符串是不是空格(<xxx>)或者是“<xxx >”，如果是“<xxx>”那么这个标签
没有属性和属性值（百分百认为没有），如果是“<xxx >”这种情况，那么可能有属性也可能没有，所以现在分两种情况考虑，第二种情况又分两种情况考虑。
等等，这里还分另外一种情况考虑，如：“<xx  />”或：“<xx cc="aa"/>”,这里的第三种情况是“<br/>”或者是第四中情况“<br   />”，还有判断是不是换行。
还有，不可能出现这种情况“< xx dd="zz">”或许是这种情况“<\nxx dd="zz">”,所以下面这里判断的是标签名称之后的的空格，或者换行其它的。*/
//                        if (string.equals(" ") || string.equals(">") || string.equals("/") || string.equals("\r") || string.equals("\n")) {//得小心“\”
                        //如果是第一种情况“<xxx>”就另外写方法分析，提取标签的名称和节点内的内容，移动下标
                        if (w文字[i] == '>') {
                            //保存当前节点的名称，和继续解析实体内容
                            ht.set名称(标签或html.substring(标签开始名称 + 1, i).trim());
                            i = 无属性标签解析(标签或html, w文字, i, ht);
                            list.add(ht);
                            break;//跳出for循环
                        } else if (w文字[i] == '/') {//如果是指针情况就好办了
                            ht.set名称(标签或html.substring(标签开始名称 + 1, i));
                            list.add(ht);
                            break;
                        } else if (w文字[i] == ' ' || w文字[i] == '\r' || w文字[i] == '\n') {
//如果是这种情况就复杂了，这个分为几种情况分析，第一“<xx  >”，二“<xx \n>”,三、<xx />、4“<xx ss="dd" />”，5“<xx ss="dd"\n/>”
                            ht.set名称(标签或html.substring(标签开始名称 + 1, i));
                            i = 有属性内容解析(标签或html, w文字, i, ht);
                            list.add(ht);
                            break;
                        }
//                            break;
//                        }
                    }
                }
            }
        }
        return list;
    }

    int 有属性内容解析(String 标签, char[] ch, int i, Xml ht) {
        int k = i + 1;
        for (int j = k; j < ch.length; j++) {
//            String s = ch[j] + "";
            if (ch[j] != ' ' && ch[j] != '\n' && ch[j] != '\r') {
                //不是空格和换行的话，是这种情况：第一“<xx  >”，二“<xx \n>”,三、<xx />、4“<xx ss=dd >”，5“<xx ss=dd />”
//先判断是不是“<xx >”和“<xx \n>”,总之都是判断为“>”,要是这个就是无属性,有内容
                if (ch[j] == '>') {
                    return 无属性标签解析(标签, ch, j, ht);//又可以重复使用这个方法，现在我知道老师为什么说要细分了，说不定哪天派上用场
                } else if (ch[j] == '/' && ch[j + 1] == '>') {//这种情况是“<xx  />”,这种情况是无内容，无属性
                    return j + 1;
                } else {//如果不是上述的情况，那么下面的情况为“<xx ss="text/html" >”或者“<xx ss="text/html" />”
                    return 内容属性解析(标签, ch, j, ht);
                }
            }
        }
        return k;
    }

    /**
     * 这个方法我们要提取标签““<xx ss="text/html" >”中的“ss="text/html"”字符串，提取属性内容
     */
    int 内容属性解析(String 标签, char[] ch, int i, Xml ht) {
        int k = i;
        while (k < ch.length) {
            if (ch[k] == '/' && ch[k + 1] == '>') {//这种情况为：“<xx ss="text/html" />”
                提取属性值(标签, i, k, ht);
                return k + 1;
            } else if (ch[k] == '>') {
                提取属性值(标签, i, k, ht);//注意是i+ht.get内容().length()
                return 无属性标签解析(标签, ch, k, ht);
            }
            k++;
        }
        return k;
    }

    void 提取属性值(String 标签, int 开始位置, int 结束位置, Xml ht) {
        String 属性和属性值 = 标签.substring(开始位置, 结束位置).trim();
//一般属性的“=”号后面的属性值都是有“""”,号，所以根据中国特点就这样做，
        int 属性开始位置 = 0, 属性结束位置, 属性值开始位置 = 0, 属性值结束位置;
        char[] cc = 属性和属性值.toCharArray();
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < cc.length; i++) {
            if (cc[i] == '=') {
                String 属性 = 属性和属性值.substring(属性开始位置, i).trim();//去掉前后的空格
                while (i < cc.length) {//即使是这种情况“type  =   "submit"   ”也能够解析得出来，即使是“type\n=\n"submit"”
                    if ((cc[i] + "").equals("\"") && 属性值开始位置 == 0) {
                        属性值开始位置 = i + 1;
                    } else if ((cc[i] + "").equals("\"") && 属性值开始位置 != 0) {
                        属性值结束位置 = i;
                        String 属性值 = 属性和属性值.substring(属性值开始位置, 属性值结束位置);
                        map.put(属性, 属性值);
                        属性值开始位置 = 0;
                        属性开始位置 = i + 1;
                        break;
                    }
                    i++;
                }
            }
        }
        if (map.size() != 0)
            ht.set属性属性值(map);
    }

    private int 无属性标签解析(String 标签, char[] ch, int i, Xml ht) {
        int k = i + 1;
        int 下标 = 同名标签内容提取(标签, ht.get名称(), k, ch);
        if (is结束标记) {
            ht.is为实体内容 = true;
            ht.set内容(标签.substring(k, 下标));
            return 下标 + 2 + ht.get名称().length() - 1;//记住要减1
        } else {
            ht.is为实体内容 = false;
            ht.set内容(标签.substring(k, 标签.length()));
            return 标签.length();//如果出现问题就返回k-1
        }
    }

    private int 同名标签内容提取(String 标签, String 标签名称, int i, char[] ch) {
        int k = i;
        String 结束标记 = "</" + 标签名称 + ">", 同名标签 = "<" + 标签名称;
        int 标记 = 0;
        for (; i <= ch.length - 结束标记.length(); i++) {//注意。这里要判断为“i<=ch.length-结束标记.length()”，而不是“i<ch.length-结束标记.length()”
            String 截取结束标记 = 标签.substring(i, i + 结束标记.length());
            String 截取同名标签 = 标签.substring(i, i + 同名标签.length());
            if (截取结束标记.equals(结束标记)) {
                标记--;
                if (标记 == -1) {
                    is结束标记 = true;
                    return i;
                }
            } else if (截取同名标签.equals(同名标签)) {
                标记++;
            }
        }
        is结束标记 = false;
        return k;
    }

    int 删除注释(char[] ch, int i) {
        int k = i + 1, 位置 = 0;
        while (k < ch.length) {
//注释里面也可能有“<”和“>”,不能够疏忽
            if (ch[k] == '<') {
                位置++;
            } else if (ch[k] == '>') {
                if (位置 == 0) {
                    return k;
                } else {
                    位置--;
                }
            }
            k++;
        }
        return k;
    }

    /**
     * 实体内容提取@return
     */
    public static String 内容提取(Xml bq) {
        int bj = 0;
        StringBuilder builder = new StringBuilder();
        char[] ch = bq.get内容().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '<' && ch[i + 1] == '/') {
                bj = bj - 1;
                while (i < ch.length) {
                    if (ch[i] == '>') break;
                    i++;
                }
            } else if (ch[i] == '<') {
                boolean is结束标记=false;
//                Map<String, Object> map = is节点(i, ch);
//                Boolean b = (Boolean) map.get("boolean");
//                i = (Integer) map.get("i");
                i++;
                while (i < ch.length) {
                    if (ch[i] == '>') {
//                        map.put("i", i);
                        if (ch[i - 1] == '/'){
                            is结束标记=true;
                            bj--;   i++;
                            if(i >=ch.length) return builder.toString();
//                            map.put("boolean", true);return map;
                            break;
                        }
                    }
                    i++;
                }
//                if (b == null) bj = bj + 1;  if(!is结束标记) bj++;
            }
            //放在后面这里
//            if (i < ch.length) string = ch[i] + "";
            if(i >=ch.length) return builder.toString();
            if (bj == 0 && ch[i] != '\n' && ch[i] != '>') {
                builder.append(ch[i] + "");
            }//不能else
        }
        return builder.toString();
    }

//    public static HashMap<String, Object> is节点(int i, char[] ch) {
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        while (i < ch.length) {
//            if (ch[i] == '>') {
//                map.put("i", i);
//                if (ch[i - 1] == '/') map.put("boolean", true);
//                return map;
//            }
//            i++;
//        }
//        map.put("i", i - 1);
//        return map;
//    }

    public static boolean 不解析的内容(String 标签, String[] 不解析内容的标签) {
        boolean bj = false;
        for (int i = 0; i < 不解析内容的标签.length; i++) {
            if (不解析内容的标签[i].equals(标签)) bj = true;
        }
        return bj;
    }
}
