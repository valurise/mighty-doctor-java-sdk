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

```java
String sid = "xxxxx"; //服务号

//userInput参数用来提交用户的答案（第一题可以传null）,包含3种题型:
//单选题
UserInput userInput = new SingleSelection(...);
//多选题
UserInput userInput = new MultiSelection(...);
//输入题
ValueEntry userInput = new ValueEntry(...);

//获取下一个问题/结论
SdkHttpResult result = ApiHttpClient.fetchQuestion(cid, secretKey, sid, userInput);

```

```java
String sid = "xxxxx"; //服务号

//回退到上一个问题
SdkHttpResult result = ApiHttpClient.rollbackQuestion(cid, secretKey, sid);

```


