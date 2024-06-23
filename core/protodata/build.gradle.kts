plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.luisfagundes.steamhunter.core.protodata"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

androidComponents.beforeVariants { variant ->
    android.sourceSets.maybeCreate(variant.name).apply {
        java.srcDirs(layout.buildDirectory.dir("generated/source/proto/${variant.name}/java"))
        kotlin.srcDirs(layout.buildDirectory.dir("generated/source/proto/${variant.name}/kotlin"))
    }
}

dependencies {
    api(libs.protobuf.kotlin.lite)
}
