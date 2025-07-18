# Uno Game Engine – Java

This is a Java-based Uno game engine built using object-oriented design principles. It is intended for developers to create their own Uno game variations with minimal effort by extending an abstract `Game` class.

## 🔧 How to Use

To run the default game:
```java
Game game = new DefaultGame();
game.play();
```

To run the custom game (uncomment the following line in `GameDriver.java`):
```java
Game game = new CustomGame();
```

## 🧠 Notes

- Designed for reusability and extensibility  
- Uses core OOP principles like inheritance and abstraction  
- Suitable for developer use, not direct gameplay by users
