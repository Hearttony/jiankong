package com.regongzaixian.jiankong.model;

import java.io.Serializable;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:36
 * Description:
 */
public class UserEntity implements Serializable {


    /**
     * jwt : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W10sImZpbGVzQ3JlYXRlZCI6W10sImluc3RydW1lbnRzIjpbXSwiZGV2aWNlcyI6W10sInRlc3RwbGFucyI6W10sInRlc3RkYXRhIjpbXSwic3RhbmRhcmRzIjpbXSwidXNlcm5hbWUiOiJKb2huIERPRSIsImVtYWlsIjoiY29udGFjdEBjb21wYW55LmNvbSIsInBhc3N3b3JkIjoiJDJhJDEwJHR3d3lMbUtKRmVLNVNndVA2bDljbXVkODNoeDRydXZiL1NVSHVka1c2NXZ2UFpqdG9xQjFxIiwiaWRfcmVmIjoiMSIsImxhbmciOiJ6aF9DTiIsInRlbXBsYXRlIjoiZGVmYXVsdCIsInByb3ZpZGVyIjoibG9jYWwiLCJjcmVhdGVkQXQiOiIyMDE3LTAzLTI3VDE0OjExOjI5LjI4NFoiLCJ1cGRhdGVkQXQiOiIyMDE3LTAzLTI3VDE0OjExOjI5LjI4NFoiLCJpZCI6MiwiaWF0IjoxNDkwNjIzODg5fQ.Qq2TvHU6Eh9Ekz4OSGE9ZHCiikC4V5q9CchsgKi2Cm4
     * user : {"username":"John DOE","email":"contact@company.com","id_ref":"1","lang":"zh_CN","template":"default","provider":"local","createdAt":"2017-03-27T14:11:29.284Z","updatedAt":"2017-03-27T14:11:29.284Z","id":2}
     */

    private String jwt;
    private UserBean user;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable {
        /**
         * username : John DOE
         * email : contact@company.com
         * id_ref : 1
         * lang : zh_CN
         * template : default
         * provider : local
         * createdAt : 2017-03-27T14:11:29.284Z
         * updatedAt : 2017-03-27T14:11:29.284Z
         * id : 2
         */

        private String username;
        private String email;
        private String id_ref;
        private String lang;
        private String template;
        private String provider;
        private String createdAt;
        private String updatedAt;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId_ref() {
            return id_ref;
        }

        public void setId_ref(String id_ref) {
            this.id_ref = id_ref;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
