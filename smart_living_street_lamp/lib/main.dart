import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

import 'api.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Smart Street Lamps',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool _smartLampEnabled = false;
  String? _error;

  StreamSubscription<Position>? _positionStreamSubscription;
  Position? _lastPosition;

  static const LocationSettings locationSettings = LocationSettings(
    accuracy: LocationAccuracy.best,
    distanceFilter: 0,
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Smart Street Lamps'),
      ),
      body: Container(
        padding: const EdgeInsets.all(20),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                'Enable/Disable Smart Street Lamp Activation',
              ),
              Switch(
                value: _smartLampEnabled,
                onChanged: onEnabledChanged,
              ),
              const SizedBox(height: 20),
              Text(
                'Last position: ${_lastPosition?.latitude ?? ''}, ${_lastPosition?.longitude ?? ''}',
              ),
              Text(
                'at ${_lastPosition?.timestamp ?? ''}',
              ),
              Text('with accuracy of: ${_lastPosition?.accuracy ?? ''}m'),
              const SizedBox(height: 20),
              Text(
                _error ?? '',
                style: TextStyle(color: Theme.of(context).colorScheme.error),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void onEnabledChanged(bool newValue) async {
    setState(() => _smartLampEnabled = newValue);

    if (newValue) {
      final serviceEnabled = await Geolocator.isLocationServiceEnabled();
      if (!serviceEnabled) {
        // Location services are not enabled don't continue
        // accessing the position and request users of the
        // App to enable the location services.
        return setState(() => _error = 'Location services are disabled');
      }

      var permission = await Geolocator.checkPermission();
      if (permission == LocationPermission.denied) {
        permission = await Geolocator.requestPermission();
        if (permission == LocationPermission.denied) {
          // Permissions are denied, next time you could try
          // requesting permissions again (this is also where
          // Android's shouldShowRequestPermissionRationale
          // returned true. According to Android guidelines
          // your App should show an explanatory UI now.
          return setState(() => _error = 'Location permissions are denied');
        }
      }

      if (permission == LocationPermission.deniedForever) {
        // Permissions are denied forever, handle appropriately.
        return setState(() => _error = 'Location permissions are denied forever');
      }

      // enable location updates
      _positionStreamSubscription = Geolocator.getPositionStream(locationSettings: locationSettings)
          .listen((position) => onPositionChanged(position));
    } else {
      _positionStreamSubscription?.cancel();
    }
  }

  Future<void> onPositionChanged(Position position) async {
    print('Position changed: $position');
    setState(() => _lastPosition = position);
    // send backend request to activate/deactivate street lamps
    await Api.sendLocationUpdate(position.latitude, position.longitude);
  }
}
