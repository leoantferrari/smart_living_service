import 'dart:convert';

import 'package:http/http.dart' as http;

class Api {
  static const String baseUrl = 'https://smart-living-module.azurewebsites.net';

  /// send location update to the backend
  /// https://smart-living-module.azurewebsites.net/light/activate
  /// {
  ///     "distance":2,
  ///     "coordinates": {
  ///         "lon": 0,
  ///         "lat":0
  ///     }
  /// }
  static Future<void> sendLocationUpdate(double lat, double lon, [double distance = 15]) async {
    print('Sending location update to backend with lat: $lat, lon: $lon, distance: $distance');
    final url = Uri.parse('$baseUrl/light/activate');
    final body = {
      'distance': distance,
      'coordinates': {
        'lon': lon,
        'lat': lat,
      },
    };
    var response = await http.put(
      url,
      body: json.encode(body),
      headers: {
        'Content-Type': 'application/json',
      },
    );
    print('Received response: ${response.body} with status code: ${response.statusCode}');
  }
}
