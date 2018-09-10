# Android SDK SYNC &MIRROR SERVICE
-  disk :65GB +

### auto sync schedule
```$xslt

    android sdk sync schedule cron="0 15 1 * * ?"

    android studio  sync schedulecron="0 15 3 * * ?" 
```
service http://localhost:8081/

## Boot Config

## service config

such as
`http://ip:8081/sync/config?local=/data/sdkmirror`
config repo root folder by local params use `local` default folder path is `/data/sdkmirror`
## sync once 
you can init force by access `http://ip:8081/sync/yes` manual, you can do it once





# endUser

developer can view this mirror address `http://ip:8081/`

```shell
export SDK_TEST_BASE_URL=http://ip:8081/android/repository/
open -a 'Android Studio'
```


