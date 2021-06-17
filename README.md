# JClipboard (Java Clipboard Library)
![GitHub Version](https://img.shields.io/github/v/release/DavidHancu/JClipboard)

The goal of this project is to offer an easy way of accessing the Clipboard using a simple Java Interface. Here are some of the features of this library:
- Simple, thread-safe, and non-blocking (Everything is done through the ``JClipboard`` class)
- Provides a simple Event system that is done by extending a class
- Easy access for the most common operations (retrieve, edit, clear, contains, etc)
- Small but feature-packed

## Maven
You can add this dependency using JitPack. Please follow the next steps:
1. Add the JitPack repository:
```xml
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
```
2. Add the dependency.
```xml
	<dependency>
	    <groupId>com.github.DavidHancu</groupId>
	    <artifactId>JClipboard</artifactId>
	    <version>{version}</version>
	</dependency>
```
Replace {version} with the version you want. The latest version is `1.0.0`.

## Examples
### Getting the JClipboard object
```java
    JClipboard jClipboard = JClipboard.getDefault();
```
### Different Text Operations
```java
    JClipboard jClipboard = JClipboard.getDefault();

    System.out.println("Clipboard Name: " + jClipboard.getName()); // Get the clipboard name

    jClipboard.clear(); // Clear the clipboard content
    System.out.println("Text after clear: " + jClipboard.getText()); // Get the text

    System.out.println("Has text: " + jClipboard.hasText()); // Check if the clipboard has text

    jClipboard.setText("hello"); // Set the text in the clipboard
    System.out.println("New text: " + jClipboard.getText()); // Get the text
```
### Event Handling
```java
    JClipboard jClipboard = JClipboard.getDefault();
    jClipboard.addEventListener(new JClipboardEventListener() {
        @Override
        public void onClipboardTextChange(JClipboardEvent event) {
            System.out.println("Was: " + event.getOldValue()); // The old value
            System.out.println("Is: " + event.getNewValue()); // The new value
        }
    });
    
    // You can also remove the event listener by using removeEventListener().
```
