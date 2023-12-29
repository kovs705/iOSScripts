val generateIos: TaskProvider<Task> by tasks.registering {
    if (isFamily(FAMILY_MAC)) {
        val xcodeGenPath = "/usr/local/bin/xcodegen"
        val specPath = "${project.rootDir}/_____IOS___FOLDER____/Project.yml"
        doLast {
            exec {
                commandLine(xcodeGenPath, "generate", "--spec", specPath)
            }
        }
    } else {
        println("Task generateIos skipped, host is not MAC OS")
    }

}
