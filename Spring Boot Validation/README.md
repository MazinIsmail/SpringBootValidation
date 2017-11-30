http://localhost:8080/people

{
  "name": "Maz",
  "sex": "M",
  "age": 19,
  "dateOfBirth": "23/10/1988",
  "phone":"1234567890",
  "email": "mazin.ismail@cognizant.com",
  "int1To16Digit": "1234567890",
  "innerStoreRequest" : {
  	"checkInner": "Hehe"
  }
}

http://localhost:8080/peopleList

{
  "name": "Maz",
  "innerStoreRequestList": [
  	{
  	"checkInner": null
    },
    {
  	"checkInner": null
    }
  ]
}

http://localhost:8080/crossField

{
  "name": "Mazin",
  "nameDependent": "test"
}