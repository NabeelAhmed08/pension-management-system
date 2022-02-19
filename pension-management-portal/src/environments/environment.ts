// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false
};

// export const baseUrlAuth =    'http://localhost:8400/auth/api/v1/';
// export const baseUrlDetails = 'http://localhost:8200/pensioner/api/v1/';
// export const baseUrlProcess = 'http://localhost:8100/process/api/v1/';



export const baseUrlAuth =    'http://authpms839214-env.eba-mbpwkeeh.us-east-1.elasticbeanstalk.com/auth/api/v1/';
export const baseUrlDetails = 'http://pension-details.us-east-1.elasticbeanstalk.com/pensioner/api/v1/';
export const baseUrlProcess = 'http://process-pension.us-east-1.elasticbeanstalk.com/process/api/v1/';



/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
