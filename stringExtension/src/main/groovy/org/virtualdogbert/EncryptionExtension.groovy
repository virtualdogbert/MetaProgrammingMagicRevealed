package  org.virtualdogbert

import groovy.transform.CompileStatic

@CompileStatic
class EncryptionExtension {
    static String encrypt(final String self) {
        "*** $self ***"
    }
}
