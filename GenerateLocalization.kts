val generateLocalization: TaskProvider<Task> by tasks.registering {
    dependsOn("linkPodDebugFrameworkIosArm64")

    fun generateIOSLocalizableStrings(androidStrings: String): String {
        val regex = Regex("<string name=\"(.*?)\">(.*?)</string>")
        val matches = regex.findAll(androidStrings)
        val iosStrings = StringBuilder()

        for (match in matches) {
            val key = match.groupValues[1]
            val value = match.groupValues[2]
            iosStrings.append(
                "\"$key\" = \"$value\";\n"
                    .replace("%s", "%@")
                    .replace("%d", "%ld")
                    .replace("%02d", "%02ld")
                    .replace("%f", "%.2f")
            )
        }

        return iosStrings.toString()

    }

    doLast {
        println("Starting localizations")
        val sourceFle = file("${project.rootDir}/__ANDROID__FOLDER___/src/main/res/values/strings.xml")
        val destinationFile = file("${project.rootDir}/__IOS__FOLDER___/LANGUAGE.lproj/Localizable.strings")

        val iosLocalization = generateIOSLocalizableStrings(sourceFle.readText())
        
        if(destinationFile.exists()) {
            destinationFile.writeText(iosLocalization)
        } else {
            println("File not found")
        }
    }
}
