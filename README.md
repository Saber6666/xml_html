
<h2>这是我第一次上传代码到github，好吧废话不多说</h2>
<i style="background:red">这个是我自己写的解析xml框架，它不但可以解析xml，还可以解析html,里面的解析算法都是我自己写的，</i>
<br/>首先下载jar包，导入到工程。
然后那怎么使用呢？首先new这个类出来<br/>
然后只需调用一个方法就可以了。<br/>
第一个参数传递的是接口回调，第二个参数传递的是要解析的xml（传html字符串也可以，html也可以解析）字符串,只需实例化接口回调和传递要解析的字符串就可以了，解析的结果通过接口回调出来,所以很简单
,这是完整的代码。
<br/>
<i> 
  String xmlstring="<FrameLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\r\n" + <br/>	
    			"    android:layout_width=\"match_parent\"\r\n" + <br/>	
    			"    android:layout_height=\"match_parent\">\r\n" + <br/>	
    			"    <!--android:divider=\"@null\"去掉分隔线-->\r\n" + <br/>	
    			"\r\n" + <br/>	
    			"    <ListView\r\n" + <br/>	
    			"        android:id=\"@+id/lv_组图\"\r\n" + <br/>	
    			"        android:layout_width=\"match_parent\"\r\n" + <br/>	
    			"        android:layout_height=\"match_parent\"\r\n" + <br/>	
    			"        android:background=\"#00CCCCCC\"\r\n" + <br/>	
    			"        android:divider=\"@null\" />\r\n" + <br/>	
    			"\r\n" + <br/>	
    			"    <GridView\r\n" + <br/>	
    			"        android:id=\"@+id/gv_组图\"\r\n" + <br/>	
    			"        android:layout_width=\"match_parent\"\r\n" + <br/>	
    			"        android:layout_height=\"match_parent\"\r\n" + <br/>	
    			"        android:background=\"#00CCCCCC\"\r\n" + <br/>	
    			"        android:divider=\"@null\"\r\n" + <br/>	
    			"        android:numColumns=\"2\"\r\n" + <br/>	
    			"        android:visibility=\"gone\" />\r\n" + <br/>	
    			"</FrameLayout>";<br/>	</i>   
        
<br/><br/>			
<font color="red">ddddddd</font>


                        Xml数据解析 xml数据解析 = new Xml数据解析();<br/>
			xml数据解析.Xml解析(new Xml数据解析.解析回调() {
                        //开始解析的时候调用这个方法
			@Override
			public void 开始解析() {
				sc("----------开始解析------------");
			}
                        //这个方法是表示正在解析的标签，解析钱回调标签名称
			@Override
			public void 正在解析的标签(String bq) {
				sc("正在解析的标签:"+bq+"\n");
				
			}
                        //这个方法是表示这个标签已经解析完成了，回调它的名称
			@Override
			public void 解析完当前(String bq) {
				sc("解析完当前:"+bq);
				sc("--------------------");
			}
                        //这个方法可以不用理会它，
			@Override
			public void 解析里面(String s) {
				sc("--------------------");
				sc("解析里面:"+s);
			}

                        //这个方法比较重要，第一个参数是正在解析的标签名称，第二个是标签的属性和属性值，解析完成后我把它放在Map集合中，第3、4、5个参数可以不用管它，具体回调什么东西把它打印出来就明白了
			@Override
			public void 解析属性和属性值(String 标签名称, HashMap<String, String> 属性属性值, String 内容, String 实体内容, Xml xml) {
				sc("标签名称:"+标签名称);
                for(Map.Entry<String,String> entry:属性属性值.entrySet()){
                    sc("属性："+entry.getKey()+"\t属性值："+entry.getValue());
                }
                sc("内容："+实体内容);
			}
                        //解析完成会回调这个方法
			@Override
			public void 解析完毕() {
				sc("解析完毕！");
			}
        	
        }, xmlstring);
                static void sc(Object o) {
                        System.out.println(o);
                    }

<br/>

这个解析xml框架的优点有：<br/>
1.使用起来简单，只需把这个类new出来，调用一个方法就可以了。<br/>
2.可以解析html字符串。<br/>
3.解析比较全面，解析完成后可以知道标签的嵌套结构，那个标签被谁嵌套<br/>
<br/>
缺点:<br/>
1.使用的内存大<br/>
2.速度有点慢，相较于其它成熟的xml解析框架。<br/>

<br/>
可能还有有未知的bug。<br/>
这是我的邮箱:2670924502@qq.com

