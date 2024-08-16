## React JS
Use npm run dev
## Springboot project
### JAVA 11 required to run
- No need to set up any DB configuration used cloud and h2 Data base.
- If you dont give Local DB details it will run on H2 database
- We have used MongoDB as well that is on cloud no need to configure that
## Log in
Use the below user pass for testing if you don't register
### Credintials
User : testuser@gmail.com
Pass: testpass

Defined DATA dummy data like flight details for this user.

You cannot see all menu untill you login.

## DATABASE

- All the table will be created if you give you local DB address in YAML file.
- If you don't give your local DB details and stop Springboot app all used data will be deleted.
- As I have configured H2 database by default.
- Pass you details in src -> main -> resources -> yaml file
  ![Alt text](./src/main/resources/yaml.png?raw=true "Title")

- To access default database Table
- url : http://localhost:8081/h2-console/
- user : sa
- pass : password
## Orders
You can see all your order in on order page under profile button

Order status will change automatically after some time for testing.

## Contact For any help
### 9503698655