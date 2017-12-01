# smip-mini
new version of smip
1.the shortlist comes later...

smip-mini for orignal SMIP ,the pure back-stage system.
for more requirements of remote,desktop,web services,the origin
system cannot afford any longer.
Instead of RESTFUL style with spring-boot web system,which can
suit this project for many situations.

####Cache:Guava
use the cache manager,default Guava.

####API-UI:Swagger2
for the RESTFUL api display.all the infos of apis shows on docs,
include the json callback and the json requirement.

####Persistance:JPA
use the jpa-repisitory,simplified the repository procedure.

####Authority 
no frame used.To post or get headers with username and password,the smip-mini 
valid it for permission.with OK, NOT_FOUND,FORBIDDEN call back
json.
