import NativePackagerKeys._

name := "scanop"

// used like the groupId in maven
organization in ThisBuild := "zm.unity"

// all sub projects have the same version
version in ThisBuild := "1.0"

scalaVersion in ThisBuild := "2.10.4"

// common dependencies
libraryDependencies in ThisBuild ++= Seq(
    "com.typesafe" % "config" % "1.2.0"
)

// this is the root project, aggregating all sub projects
lazy val scanop = Project(
    id = "scanop",
    base = file("."),
    // configure your native packaging settings here
    settings = packageArchetype.java_server++ Seq(
        maintainer := "Harrison Mfula <hmfula@yahoo.com>",
        packageDescription := "Scalable SON based prototype for cellular network optimization",
        packageSummary := "scason",
        // entrypoint
        mainClass in Compile := Some("zm.unity.frontend.ProductionServer")
    ),
    // always run all commands on each sub project
    aggregate = Seq(api, ml, cco,ts)
) dependsOn(api, ml, cco,ts) // this does the actual aggregation

// --------- Project ML ------------------
lazy val ml = Project(
    id = "ml",
    base = file("ml")
) dependsOn(api)



// --------- Project API ------------------
lazy val api = Project(
    id = "api",
    base = file("api")
)

// --------- Project CCO ----------------
lazy val cco = Project(
    id = "cco",
    base = file("cco")
) dependsOn(api)

// --------- Project TS ----------------
lazy val ts = Project(
    id = "ts",
    base = file("ts")
) dependsOn(api)

