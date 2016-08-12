绝世好医 JAVA SDK
===========================
绝世好医服务接口API

## 功能列表
- 获取症状列表
- 获取服务号
- 获取问题/结论节点
- 回退到上一个问题节点

## Usage

```java
//请联系绝世好医团队获取
String cid = "yourcid";
String secretKey = "yoursecretkey";

//获取症状列表
SdkHttpResult result = ApiHttpClient.getAllSymptom(cid, secretKey);

```

```java
String userId = "U001";//用户唯一标识ID
String symptomId = "419"; //症状ID
String sex = "1";//性别:1:男性,2:女性
String birthday = "1980-01-01"; //生日:yyyy-MM-dd
String height = "170"; //身高(cm)
String weight = "70"; //体重(kg)

//获取服务号
SdkHttpResult result = ApiHttpClient.startSession(cid, secretKey, userId, symptomId, sex, birthday, height, weight);

```


