package Bitrix

 class Config {
    final static Properties properties = new Properties()
     final static FileInputStream inputStreamReader

     static {
         try {
             inputStreamReader = new FileInputStream("src/test/resources/localSetting.properties")
             properties.load(inputStreamReader)
         }
         catch (IOException e){
             throw new RuntimeException(e)
         }
     }
     static String getProperty(String key){
         return getProperties().getProperty(key)
     }


}
