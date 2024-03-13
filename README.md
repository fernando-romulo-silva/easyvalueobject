# easyvalueobject

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Project status](https://img.shields.io/badge/Project%20status-Maintenance-orange.svg)](https://img.shields.io/badge/Project%20status-Maintenance-orange.svg)

# Project status

I use this project to learn new technologies related to Java and frameworks like Apache utils, Guava, etc.
I change this project constantly improving and adding new feature, click [here](docs/STATUS.md) to follow up.

# About

The purpose of this project is create a lib that implement the value object pattern of the commons attributes like email, barcodes, etc. The project is separate in modules: 
 - easyvalueobject-api: module with commons classes for all modules 
 - easyvalueobject-international: module for international value objects (email, barcodes, login, password, etc)
 - easyvalueobject-brazil: module for brazilian value objects (CPF, CNPJ, CNH, etc)
 
 # Technologies

- Java
- Ant
- Maven
- TestNG
- AssertJ
- Apache Libs

# Install

requirements (environment variables configured): 
 - Java 11
 - Maven 3
 - Git
 
```bash
# clone it
git clone https://github.com/fernando-romulo-silva/easyvalueobject

# access the project folder
cd easyvalueobject

# execute
mvn install
```

# How to Use

First add it as lib, in pom.xml, add the following xml between `<dependencies> ... </dependencies>`

```xml
<dependency>
	<groupId>org.easyvalueobject</groupId>
	<artifactId>easyvalueobject-international</artifactId>
	<version>${easyvalueobject-version}</version>	
</dependency>
```