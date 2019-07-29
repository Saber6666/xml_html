package htmlxml标签解析工具.逻辑;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.text.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;/*
列表：1、数数组最大值。
2、int型数组冒泡排序（有必要重载double型的数组)。*/
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class 静态工具包 {
    //传个int类型的数组求最大值方法
    public static int 最大值(int[] arr) {
        int aa = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aa) {
                aa = arr[i];
            }
        }
        return aa;
    }

    private static void 冒泡排序(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int k = 0; k < a.length - 1; k++) {
                if (a[k] > a[k + 1]) {
                    int t = a[k];
                    a[k] = a[k + 1];
                    a[k + 1] = t;
                }
            }
        }
    }

    public void 选择排序(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int k = i + 1; k < a.length; k++) {
                if (a[i] < a[k]) {
                    int t = a[i];
                    a[i] = a[k];
                    a[k] = t;
                }
            }
        }
    }

    private static void 解元一次方程(int a, int b, int c) {
        double x1 = (-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        double x2 = (-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        DecimalFormat formatter1 = new DecimalFormat("0.0000");//设定输出的精度控制精度
        System.out.println("精确度控制在0.0000");
        System.out.println("解得x1=" + formatter1.format(x1));//设定精度输出
        System.out.println("解得x2=" + formatter1.format(x2));
    }

    /*
     *竖条形图生成器,
     *这是我写的“条形图生成器”，未来我可能加入我制作的静态工具包里面
     * */
    public static String 条形图(int rs, int[] cj) {
        String 图形 = "";
        String[] fw = new String[rs];
        int i;
        String su = "";
        int ss = cj[0];
        for (int ww = 0; ww < rs - 1; ww++) {//判断传入的数字哪个最大，并把最大的选出来
            if (ss < cj[ww + 1]) {
                ss = cj[ww + 1];
            }
        }
        for (int s = ss; s > 0; s--) {
            for (int aa = 0; aa < rs; aa++) {
                if (cj[aa] < s) {
                    su = su + "\t\t\t\t";
                } else {
                    su = su + "\t💔\t";
                    //su=su+" &";//有趣
                }
            }
            su = su + "\n";
        }
        图形 = 图形 + su + "\n";
        int[] 排 = new int[rs];
        for (int ww = 0; ww < rs; ww++) {
            排[ww] = cj[ww];
            图形 = 图形 + "\t" + cj[ww] + "\t";
        }
        for (int sq = 0; sq < 排.length - 1; sq++) {
            for (int k = sq + 1; k < 排.length; k++) {
                if (排[sq] < 排[k]) {
                    int t = 排[sq];
                    排[sq] = 排[k];
                    排[k] = t;
                }
            }
        }
        图形 = 图形 + "\n";
        for (int gg = 0; gg < 排.length; gg++) {
            for (int zz = 0; zz < 排.length; zz++) {
                if (cj[gg] == 排[zz]) {
                    图形 = 图形 + "第" + (zz + 1) + "名\t";
                    break;
                }
            }
        }
        return 图形;
    }

    public static String 条形图2(int[] cj) {
        int rs = cj.length;
//		int i;
        String su = "";
        System.out.println("排行");
        int ss = cj[0];
        for (int ww = 0; ww < rs - 1; ww++) {
            if (ss < cj[ww + 1]) ss = cj[ww + 1];
        }
        for (int s = ss; s > 0; s--) {
            for (int aa = 0; aa < rs; aa++) {
                if (cj[aa] < s) {
                    su = su + "\t";
                } else {
                    su = su + "\t💔";
                    //su=su+" &";//有趣
                }
            }
            su = su + "\n";
        }
        int[] 排 = new int[rs];
        for (int ww = 0; ww < rs; ww++) {
            排[ww] = cj[ww];
            su = su + "\t" + cj[ww];
        }
        for (int sq = 0; sq < 排.length - 1; sq++) {
            for (int k = sq + 1; k < 排.length; k++) {
                if (排[sq] < 排[k]) {
                    int t = 排[sq];
                    排[sq] = 排[k];
                    排[k] = t;
                }
            }
        }
        su = su + "\n";
        for (int gg = 0; gg < 排.length; gg++) {
            for (int zz = 0; zz < 排.length; zz++) {
                if (cj[gg] == 排[zz]) {
                    su = su + "\t第" + (zz + 1) + "名";
                    break;
                }
            }
        }
        return su;
    }

    /*下面两个方法是字符串区间拆分器*/
    public static String 字符串区间拆分器(String 原字符串, String 区间1, String 区间2) {
        String[] i = 原字符串.split(区间1, 2);
        String[] j = i[1].split(区间2, 2);
        String s = j[0];
        return s;
    }

    /**
     * @参数选择：选择要截取字符串的区间，这个字符串被拆成3段，可以通过输入字符串来“左边、右边、中间”来选择，我得重载一个方法， 输入数字来选择一段的字符串
     */
    public static String 字符串区间拆分器(String 原字符串, String a, String b, String 选择) {
        String s = "";
        String[] i = 原字符串.split(a, 2);
        //b-1这里有一个问题，万一截取出来的数组“i”长度为1。执行下面的代码String[]j=i[1].split(b,2)就会发生异常，所以这里就做判断
        if (i.length == 1) return null;
        String[] j = i[1].split(b, 2);
        //b-2这里也是做判断
        if (i.length == 1) return null;
        if (选择.equals("左边")) {
            s = i[0];
        } else if (选择.equals("中间")) {
            s = j[0];
        } else if (选择.equals("右边")) {
            s = j[1];
        } else {
            s = i[0] + j[0] + j[1];
        }

        return s;
    }

    public static String 字符串区间拆分器(String 原字符串, String a, String b, int 选择) {
        String s = "";
        String[] i = 原字符串.split(a, 2);
        if (i.length == 1) return null;

        String[] j = i[1].split(b, 2);
        if (i.length == 1) return null;

        if (选择 == 0) {
            s = i[0];
        } else if (选择 == 1) {
            s = j[0];
        } else if (选择 == 2) {
            s = j[1];
        } else {
            s = i[0] + j[0] + j[1];
        }
        return s;
    }

    /**
     * 文件搜索器工具包____________________________________
     *
     * @版本0.01
     * @参数见参数
     */
    public static String 文件搜索器(String 始目录, String 目标文件) {
        String 文件 = "";
        File[] 数据 = {};
        File wj = new File(始目录);
        if (wj.isDirectory()) {
            数据 = wj.listFiles();
            if (数据 == null) {
                return null;/*不知道是电脑的文件的问题还是系统的问题，在手机测试没有问题，但是在这里测试不知道为什么报空指针异常，
					 难道文件夹还有null?可能是系统的特殊文件在这里扫描被误认为文件夹，导致扫描文件不不可能存在文件夹的，所以为null，这是我猜测系统的特殊文件导致的，所以在这里
					 如果为null就结束这个函数，原本我想try...catch掉的。*/
            }
        } else {
            return null;
        }
        for (int i = 0; i < 数据.length; i++) {
            if (文件 != null) {
                if ((文件 = 匹配搜索文件(数据, 目标文件)) != null) return 文件;
            }
            if ((文件 = (文件搜索器(数据[i].toString(), 目标文件))) != null) return 文件;

        }
        return null;
    }

    private static String 匹配搜索文件(File[] shuliang, String 目标文件) {//注意这要私有修饰，现在我明白为什么要私有修饰了
        String 文件 = "";
        for (int i = 0; i < shuliang.length; i++) {
            String[] 拆分 = shuliang[i].getName().split("\\.");//拆分特殊字符串需要转意
            if (拆分[0].equals(目标文件)) {
                System.out.println(shuliang[i].toString());
                return 文件;
            }
        }
        return null;
    }
    //文件搜索器______________________文件搜索器另外更新在一个文件里面__________________________________________

    /**
     * 将输入流（前提是这个流是文本）转换成字符串
     * 第二个参数是字符串的编码形式
     */
    public static String 输入流转字符串(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int cd = 0;
        String result = "";
        if (inputStream != null) {
            try {
                while ((cd = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, cd);
                }
                outputStream.flush();
                data = outputStream.toByteArray();
                result = new String(data, 0, data.length, encode);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) outputStream.close();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //——————————————————————————————————————

    /**
     * 这下面的方法已经过时了，其实可split("hh")方法代替，我没想到用split("hh")方法就可以了，不过练练也好，唯一能够用的方法是“拆掉指定字符串”方法。 刚才用了split("xxccxx")方法，有缺陷，那就是拆分"xxcxx(c)xx".split("(c)")就会出现问题，所以我写的这个api还是有用的，没有过时。。。。。。
     * <p>
     * 这个是删除指定字符串的api，比如“0123456”，想删除“234”就传原字符串进来和要删除的字符串“234”，删除后悔返回“0156”
     *
     * @删除的字符串 要删除的字符串
     * @返回 组合好的字符串
     * 传入要删除的字符串就会返回已经删除好的字符串
     */
    public static String 删除指定字符串(String 原字符串, String 删除字符串) {
        String 删除 = null;
        String 筛选 = null;
        for (int i = 0; i < 原字符串.length() - 删除字符串.length() + 1; i++) {
            if (原字符串.substring(i, i + 删除字符串.length()).equals(删除字符串)) {
                删除 = 原字符串.substring(0, i) + 原字符串.substring(i + 删除字符串.length(), 原字符串.length());
                //不结束返回可能会出现小小问题，并无大碍
                return 删除;
            }
        }
        return 删除;
    }
    //或者我可以反向来去特定区域的字符串

    /**
     * 这个方法和String的substring方法相反，拆掉指定位置的字符串，substring方法是保留指定的字符串。
     */
    public static String 拆掉指定字符串(String string, int 起始位置, int 最后位置) {
        StringBuffer s = new StringBuffer();
        char[] 字符串数组 = string.toCharArray();
        for (int i = 0; i < 字符串数组.length; i++) {
            if (i <= 起始位置 || i > 最后位置) s.append(字符串数组[i]);
        }
        return s.toString();
    }

    /**
     * 此外还没有完，我又在“拆掉指定字符串”的基础上面进行重载“删除指定字符串”方法
     * 这个是删除指定字符串的api，比如“0123456”，想删除“234”就传原字符串进来和要删除的字符串“234”，删除后悔返回“0156”
     */
    public static String 删除指定字符串2(String 原字符串, String 删除字符串) {
        String 删除 = null;
        String 筛选 = null;
        for (int i = 0; i < 原字符串.length() - 删除字符串.length() + 1; i++) {
            if (原字符串.substring(i, i + 删除字符串.length()).equals(删除字符串)) {
                //就算i-1是负数也没有问题，刚刚好
                删除 = 拆掉指定字符串(原字符串, i - 1, i + 删除字符串.length() - 1);
                //不结束返回可能会出现小小问题，并无大碍
                return 删除;
            }
        }
        return 删除;
    }

    /**
     * 判断一段字符串是否在包含另外一段字符串,
     * 这里返回一个list集合，集合装数字，意思是被包含字符串的位置，
     */
    public static List<Integer> get字符串是否包含字符串(String 原字符串, String 被包含的字符串) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 原字符串.length() - 被包含的字符串.length() + 1; i++) {
            if (原字符串.substring(i, i + 被包含的字符串.length()).equals(被包含的字符串)) {
                list.add(i);
            }
        }
        return list;
    }

    public static List<Integer> get字符串匹配(String 原字符串, String 被包含的字符串) {
        String 大 = 原字符串, 小 = 被包含的字符串;
        if (原字符串.length() < 被包含的字符串.length()) {
            大 = 被包含的字符串;
            小 = 原字符串;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 大.length() - 小.length() + 1; i++) {
            if (大.substring(i, i + 小.length()).equals(小)) list.add(i);
        }
        return list;
    }

    /**
     * 这个方法传入的是源字符串的数组和要搜索的字符串
     *
     * @注意 new String(原字符串,i,i+被包含的字符串.length()) 这个第二个构造方法是选取字符串的下标，第三个参数是选取字符串的长度
     */
    public static List<Integer> get字符串是否包含字符串(char[] 原字符串, String 被包含的字符串) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 原字符串.length - 被包含的字符串.length() + 1; i++) {
            if (new String(原字符串, i, 被包含的字符串.length()).equals(被包含的字符串)) {
                list.add(i);
            }
        }
        return list;
    }
    //——————————————————————————————————————
    //____________字符串反转______________

    /**
     * 这个是我的方法，效率低
     */
    public static String get反转字符串1(String string) {
        char[] cs = string.toCharArray();
        String string2 = "";
        for (int i = 0; i < cs.length; i++) {
            string2 = cs[i] + string2;
        }
        return string2;
    }

    /**
     * 这个方法是我看到毕向东老师，效率高
     */
    public static String get反转字符串2(String string) {
        char[] cs = string.toCharArray();
        for (int start = 0, end = cs.length; start < end; start++, end--) {
            get数组位置调换(cs, start, end);
        }
        return new String(cs);
    }

    private static void get数组位置调换(char[] ch, int start, int end) {
        char c = ch[start];
        ch[start] = ch[end];
        ch[end] = c;
    }

    /**
     * 判断网址是否有www
     */
    private static boolean is判断网址含有www(String url) {
        boolean bj = false;
        String[] strings = url.split("//");
        String string = strings[1].substring(0, 3);
        if (string.equals("www")) bj = true;
        return bj;
    }

    public static String read内存文本(String 文件路径) {
        String string = "";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(文件路径);
            byte[] bs = new byte[1024];
            int cd;
            while ((cd = inputStream.read(bs)) != -1) {
                String string2 = new String(bs, 0, cd);
                string += string2;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "读取文件出错";
        } catch (IOException e) {
            e.printStackTrace();
            return "读取文件出错";
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    //______________________________________________________________
    public static List<String> q去除list相同字符串(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int k = i + 1; k < list.size(); k++) {
                if ((list.get(i)).equals((list.get(k)))) {
                    list.remove(k);
                    k--;
                }
            }
        }
        return list;
    }

    public static List<String> q去除list相同字符串(ArrayList<String> list) {
        ArrayList<String> list2 = new ArrayList<String>();
        for (int i = 0; i < list.size() - 1; i++) {
            //判断list2集合所有元素是否和list的一个元素相同
            if (!list2.contains(list.get(i))) list2.add(list.get(i));

        }
        return list2;
    }

    //_________
    public static String 搜索字符串出现的次数(String string) {
        String string2 = "";
        Map<String, Integer> map = new HashMap<String, Integer>();
        char[] ch = string.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (map.get(ch[i] + "") == null) {
                map.put(ch[i] + "", 1);
            } else {
                int k = map.get(ch[i] + "");
                k++;
                map.put(ch[i] + "", k);
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            string2 = string2 + "“" + entry.getKey() + "”出现的次数为：" + entry.getValue() + "\n";
        }
        return string2;
    }

    //-------------------------------------------------
    private  String 输入流转数字(byte[] data) {
        Long long1 = 0l;
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            long1++;
            if (b == -3) {
                if (long1 % 900 == 0) {
                    builder.append(b + "\n");
                } else {
                    builder.append(b + "");
                }
            } else {
                if (long1 % 900 == 0) builder.append("\n");
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    //有时间自己写个文件合并流
    public static boolean 文件合并(String[] filedir, String 新文件路径) throws IOException {
        boolean zt = false;
        Vector<FileInputStream> vector = new Vector<FileInputStream>();
        FileInputStream[] filestreams = new FileInputStream[filedir.length];
        for (int i = 0; i < filedir.length; i++) {
            filestreams[i] = new FileInputStream(filedir[i]);
            vector.add(filestreams[i]);
        }
        Enumeration<FileInputStream> enumeration = vector.elements();
        SequenceInputStream sequence = new SequenceInputStream(enumeration);
        FileOutputStream xindir = new FileOutputStream(新文件路径);
        byte[] data = new byte[1024 * 1024 * 6];
        int cd;
        while ((cd = sequence.read(data)) != -1) {
            xindir.write(data, 0, cd);
        }
        sequence.close();
        xindir.close();
        zt = true;
        return zt;
    }

    //	public static void main(String[] args) {
    //	}
}
