Compile native image as follows:

```shell
./gradlew buildNativeImage
```


Error Details:

```text
[1/8] Initializing...                                                                                    (0.0s @ 0.11GB)
Error: Could not find target method: private io.quarkus.runtime.graal.Target_org_wildfly_common_net_CidrAddress(java.net.InetAddress,int)
Internal exception: com.oracle.svm.core.util.UserError$UserException: Could not find target method: private io.quarkus.runtime.graal.Target_org_wildfly_common_net_CidrAddress(java.net.InetAddress,int)
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.util.UserError.abort(UserError.java:85)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.findOriginalMethod(AnnotationSubstitutionProcessor.java:845)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.handleMethodInAliasClass(AnnotationSubstitutionProcessor.java:408)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.handleAliasClass(AnnotationSubstitutionProcessor.java:375)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.handleClass(AnnotationSubstitutionProcessor.java:345)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.init(AnnotationSubstitutionProcessor.java:301)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGenerator.createAnnotationSubstitutionProcessor(NativeImageGenerator.java:1112)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGenerator.setupNativeImage(NativeImageGenerator.java:965)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:590)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:554)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:528)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:711)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner.start(NativeImageGeneratorRunner.java:139)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:94)
------------------------------------------------------------------------------------------------------------------------
                        0.3s (6.7% of total time) in 11 GCs | Peak RSS: 0.50GB | CPU load: 4.57
========================================================================================================================
Failed generating 'quarkus-3.16.0.cr1-native-bug-1.0.0-runner' after 4.0s.
> Task :quarkusAppPartsBuild FAILED
```