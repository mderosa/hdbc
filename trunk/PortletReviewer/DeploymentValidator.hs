
module DeploymentValidator where

import Text.Regex
import Definitions
import Validation

allErrors :: FileLines -> [ErrorMessage]
allErrors lines = validateCacheEnabled lines

validateCacheEnabled :: FileLines -> [ErrorMessage]
validateCacheEnabled fileLines = 
        let regex = mkRegex "<expiration-cache>"
            errorMsg = "!!! this portlet has not enabled cacheing.  With the current configuration all portal\n" ++
                       "requests even those not directed towards this portlet will result in a requerying\n" ++
                       "of the backend datastore.\n"
            Vldtion c e b = validateAtLeastOneLineMatches (regex, regex, errorMsg) fileLines in
        e
     