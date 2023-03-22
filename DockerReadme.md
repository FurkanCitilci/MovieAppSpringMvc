# DOCKER KULLANIMI

## IMAGE OLUSTURMA
    docker build --build-arg JAR_FILE=<build path >-t <image name> .
    docker build --build-arg JAR_FILE=/build/libs/auth-service-v.0.0.1.jar -t java5movieapp:v003 .
    build path: servisimizden build ald���m�z zaman olu�an jar dosyas�n�n konumu
    image name: olu�turaca��m�z image e verece�imiz isim ( versiyon numaras� vermeyi unutmay�n !!! )
## IMAGE UZER�NDEN CONTAINER CALISTIRMA
    docker run -d -p d�sport:i�port java5movieapp:v003
    docker run -d -p 9091:8085 java5movieapp:v003
    i�port: application yml da uygulaman�n aya�a kalkt��� port
    d��port: container�n d��ar�ya a��ld��� port istekler bu porta gelecek bu port i� porta yonlendirecek
## NETWORK OLUSTURMA
    docker network ls: var olan networklerimizi listeleme
    docker network rm somenetwork : network ad�yla networkumuzu silme
    docker network create --driver bridge --subnet <ag portlar� > --gateway 172.19.0.1 < network name>
    docker network create --driver bridge --subnet  172.19.0.1/24 --gateway 172.19.0.1 java5network
    ag portlar�: networkumuzdeki ip aral���n� belirledi�miz yer
    network create komutu ile bir network olusturabiliriz
### NETWORKE CONTAINER BAGLAMA
    docker run --name java4-postgresql -e POSTGRES_PASSWORD=9896 --net java5network -d -p 5858:5432 postgres
    java4-postgresql ad�nda bir postgresl container'� olusturduk 
    --net java4-network komutu ile olusturdugumuz java5networkune  postgresqlimizi ba�lad�k
    daha sonra apllication yml da db_url imizi de�i�tirdik
    url: jdbc:postgresql://localhost:5432/movieAppDb yerine art�k 
    jdbc:postgresql://java5postgre/movieAppDb yazd�k burda 
    localhost yerine asl�nda olusturdugumuz postgresql containern�n ismini verdik 
    ve pg adminden register ile 5858 daki postgeslimize ba�land�k ve Java4SocialMediaAuthDb ad�nda bir databse olusturduk
    daha sonra uygulamam�z� tekrar build edip uygulamam�zdan bir image olusturduk 
    docker build --build-arg JAR_FILE=/build/libs/auth-service-v.0.0.1.jar -t java5movieapp:v003 . 
    ve bu image'i �al�st�r�ken olusturdu�umuz java4-networkune asag�daki kodla ba�lad�k
    docker run --net java4-network -d -p 9095:8085 java5movieapp:v003
    
    




