PSL Mobile Foundation
===
## PinCodeAndroid
A sample application demonstrating use of the CredentialsValidation Security Check.

### Tutorials
https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/authentication-and-security/credentials-validation/

### Usage

1. Use either Maven, Mobile Foundation CLI or your IDE of choice to [build and deploy the available `ResourceAdapter` and `PinCodeAttempts` adapters](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/adapters/creating-adapters/).

 The PinCodeAttempts Security Check adapter and the Resource adapter can be found in https://github.com/Persistent-Mobile-Foundation/SecurityCheckAdapters.

2. From a command-line window, navigate to the project's root folder and run the commands:
 - `pmfdev app register` - to register the application.
 - `pmfdev app push` - to map the `accessRestricted` scope to the `PinCodeAttempts` security check.

3. Run the application in the Android Emulator or physical device. Press the **Get Balance** button and enter "1234" to display the balance.

### Supported Levels
PSL Mobile Foundation 9.0


