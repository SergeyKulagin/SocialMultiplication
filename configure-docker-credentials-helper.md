- Download appropriate release from here
```https://github.com/docker/docker-credential-helpers/releases```
- unpack it, make it executable and add to $PATH
- for linux run ```docker-credential-pass``` to double check
- install gpg and pass. ```apt install gpg pass```
- gpg --generate-key. Enter your name, mail, etc. You will get gpg-id like "5BB54DF1XXXXXXXXF87XXXXXXXXXXXXXX945A". 
- ```pass init 5BB54DF1XXXXXXXXF87XXXXXXXXXXXXXX945A```
- pass insert docker-credential-helpers/docker-pass-initialized-check and set the ``master`` password
- pass show docker-credential-helpers/docker-pass-initialized-check. You should see pass is initialized.
- docker-credential-pass list. You should see {} or another data. You shouldn`t see error like "pass store is uninitialized".
-  nano ~/.docker/config.json. Set in root node the next line "credsStore": "pass" 

