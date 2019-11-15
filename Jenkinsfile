pipeline {
    agent any
 //Opciones específicas de Pipeline dentro del Pipeline   
 options { 
 //Mantener artefactos y salida de consola para el # específico de ejecuciones recientes del Pipeline.  
 buildDiscarder(logRotator(numToKeepStr: '3')) 
 //No permitir ejecuciones concurrentes de Pipeline  
 disableConcurrentBuilds()   
 } 
  tools {     
  jdk 'JDK8_Centos' 
  //Preinstalada en la Configuración del Master     
  gradle 'Gradle5.6_Centos' 
  //Preinstalada en la Configuración del Master   
  }   
    stages {


stage('Checkout') {
steps{
echo "------------>Checkout<------------"
checkout([
$class: 'GitSCM',
branches: [[name: '*/master']],
doGenerateSubmoduleConfigurations: false,
extensions: [],
gitTool: 'Git_Centos',
submoduleCfg: [],
userRemoteConfigs: [[
credentialsId: 'GitHub_jjbserna',
url:'https://github.com/jjbserna/Tienda-Ceiba.git'
]]
])
}
}
 
stage('Build project') {
steps {
sh 'gradle --b ./Tienda/build.gradle clean'
sh 'gradle --b ./Tienda/build.gradle build'
}
}
stage('Compile & Unit Tests') {
steps{ 
echo 'Testing..'

sh '​gradle--b ./Tienda/build.gradle test​'
}
}

    }
}