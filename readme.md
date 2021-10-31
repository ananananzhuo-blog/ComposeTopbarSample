> 关注公众号学习更多知识
>
>![](https://img-blog.csdnimg.cn/img_convert/6dd2df09156ca4cbfc44ad68c9baa2e4.png)



## Material Design风格的顶部和底部导航栏
Compose中Material Design风格的设计我们的做法如下：

1、使用Scafoold作为页面的顶级，`Scafoold`中承载topbar和bottombar分别作为顶部导航栏和底部导航栏。

2、调用`WindowCompat.setDecorFitsSystemWindows(window, false)`方法让我们的布局超出状态栏和底部导航栏的位置
3、使用ProvideWindowInsets包裹布局，使我们可以获取到状态栏和底部导航栏的高度（不包裹无法获取状态栏和底部导航栏高度）
4、手动处理顶部和底部导航栏让页面适应屏幕


## 界面设计

### TopBar设计
#### 实现方式
因为使用`WindowCompat.setDecorFitsSystemWindows(window, false)`设置后页面布局顶到了状态栏的上面，因为我们需要用一个`Spacer`来填充状态栏，让我们的布局看起来正常点
#### 代码
如下是封装的状态栏方法
```kt
@Composable
fun TopBarView(title: String, callback: () -> Unit) {
    Column {
        Spacer(
            modifier = Modifier
                .statusBarsHeight()//设置状态栏高度
                .fillMaxWidth()
        )
        TopAppBar(title = {
            Text(title)
        }, navigationIcon = {
            IconButton(onClick = {
                callback()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        })
    }
}
```
#### 处理状态栏前后的ui状态
**处理前：**

![](https://files.mdnice.com/user/15648/f30f26e3-d424-475f-bd1d-1d592400fe61.png)
**处理后：**

![](https://files.mdnice.com/user/15648/62bfd4d4-7158-41ff-a6ae-599e975233ae.png)
结论是经过我们的处理后解决了状态栏的遮挡

### BottomBar设计

#### 实现方式
因为使用`ProvideWindowInsets`包裹后底部导航栏顶到了底部，所以需要填充一个底部导航栏高度的Spacer。

#### 代码


```kt
bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(statusbarColor),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "首页")
                    Text(text = "通讯录")
                    Text(text = "朋友圈")
                    Text(text = "我的")

                }
                Spacer(modifier = Modifier.navigationBarsHeight())
            }
        }
```
#### 处理状态栏前后的ui状态
**处理前：**


![](https://files.mdnice.com/user/15648/9d0e61a7-0232-455d-b702-b789e613dcca.png)


**处理后：**


![](https://files.mdnice.com/user/15648/4c33895c-d8e0-49e0-8ef9-82a8c4ae65ab.png)

结论是经过我们的处理后解决了底部导航栏的遮挡问题
### 状态栏和底部导航栏颜色的处理




### 状态栏和底部导航栏颜色设置
#### 依赖

```groovy
   implementation "com.google.accompanist:accompanist-insets:0.16.0"
    implementation "com.google.accompanist:accompanist-systemuicontroller:0.16.0"
```

#### 代码
```kt
 rememberSystemUiController().run {
                    setStatusBarColor(statusbarColor, false)
                    setSystemBarsColor(statusbarColor, false)
                    setNavigationBarColor(statusbarColor, false)
                }
```
### 整体效果
我们发现状态栏和底部导航栏的颜色都变了


![](https://files.mdnice.com/user/15648/a704a10b-68e8-4d3a-90c0-ca2c529e0c7c.png)

## 如何处理内容部分超出底部导航栏的区域
使用`WindowCompat.setDecorFitsSystemWindows(window, false)`处理了页面后，Scafoold的内容区域也会被顶到底部导航栏的下方，同样也需要我们处理

以下是处理前和处理后的代码和效果

### 处理前
#### 代码

```kt
LazyColumn() {
            items(30) { index ->
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = index.toString())
                }
            }
        }
```

#### 效果
这里只展示到第27个item，第28、29个item没有展示出来，所以需要处理才行
![](https://files.mdnice.com/user/15648/bf687e33-e1a9-4f40-ab2f-bf73648379d5.png)

### 处理后
#### 代码

```kt
 {padding->
        LazyColumn(Modifier.padding(bottom = padding.calculateBottomPadding())) {//这里会计算出距离底部的距离，然后设置距离底部的padding
            items(30) { index ->
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = index.toString())
                }
            }
        }

    }
```
#### 效果
改正后的第29个item展示了出来
![](https://files.mdnice.com/user/15648/3e222ce2-45c2-4660-b80e-94cb0c71d5e1.png)



> 代码：https://github.com/ananananzhuo-blog/ComposeTopbarSample