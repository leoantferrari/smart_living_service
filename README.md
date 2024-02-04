# Backend for Smart Living Project

This Service provides the backend endpoints for the smart living project.

## Endpoints
### Activate Lights
To activate the lights within a certain distance of the current gps location, you can do an activate light request, with information in the payload:

`GET /light/activate`

With Payload:
```json
{
    "distance":5,
    "coordinates": {
        "lon": 0,
        "lat":0
    }
}
```


### Deactivate all Lights
To deactivate all currently activated lights, make a post request to this endpoint:

`POST /manage/deactivateAll`


### Create a light
To create a new light you can call this endpoint with a the light you want to create as the payload:

`POST /manage/create`

```json
{
    "id":0,
    "lon":0,
    "lat":0,
    "isOn":false,
    "turnOnTrigger":"triggerOnURL" ,
    "turnOffTrigger":"triggerOffURL"
}
```



