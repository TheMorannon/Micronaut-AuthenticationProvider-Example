## AuthenticationProvider returns UNAUTHORIZED even when executing AuthenticationResponse.success() 
- Execute AuthenticationProviderUserPasswordTest to reproduce problem
- testAuthenticationSuccess should get HttpStatus.OK returned but gets HttpStatus.UNAUTHORIZED