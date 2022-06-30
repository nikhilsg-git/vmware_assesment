***
##### PATIENT Serivce Deployment on DEVELOPMENT Environment #####

```
Step : 1  mvn clean install -Ddocker.image.prefix=dev dockerfile:build 
```
_Ddocker-image.prefix options can be_ ** dev** or **prod**

```
Step : 2  aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 853485304571.dkr.ecr.us-east-2.amazonaws.com
```
***

```
docker tag nexogic/dev/patient:latest 853485304571.dkr.ecr.us-east-2.amazonaws.com/nexogic/dev/patient:latest
```
_tag name depends on -Ddocker-image-prefix value_

```
docker push 853485304571.dkr.ecr.us-east-2.amazonaws.com/nexogic/dev/patient:latest
```

```
aws ecs update-service --cluster dev-patient-ecs-cluster --service  dev-patient-svc  --force-new-deployment  
```


```
aws ecs update-service --cluster nex-prod-patient-cluster --service  nex-prod-patient-svc  --force-new-deployment  
```
****
***
##### PATIENT Serivce Deployment on PRODUCTION Environment #####

```
Step : 1  mvn clean install -Ddocker.image.prefix=prod dockerfile:build 
```
_Ddocker-image.prefix options can be_ ** dev** or **prod**

```
Step : 2  aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 853485304571.dkr.ecr.us-east-2.amazonaws.com
```
***

```
docker tag nexogic/prod/patient:latest 853485304571.dkr.ecr.us-east-2.amazonaws.com/nexogic/prod/patient:latest
```
_tag name depends on -Ddocker-image-prefix value_

```
docker push 853485304571.dkr.ecr.us-east-2.amazonaws.com/nexogic/prod/patient:latest
```
aws ecs update-service --cluster nex-prod-patient-cluster --service  nex-prod-patient-svc  --force-new-deployment  
```
****
