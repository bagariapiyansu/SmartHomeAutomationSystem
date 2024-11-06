# Smart Home Automation System with On/Off and Brightness/Speed Levels

## Project Overview
Design and implement a system using **Java** and **JavaFX** that allows users to control lights in different rooms of a house. Users can turn lights on/off and select predefined brightness levels (Low, Medium, High) using buttons.

## Core Features

### 1. Light Control
Users can control the light in each room:
- **On/Off Button** to toggle the light state
- **Three Brightness Buttons**: "Low", "Medium", "High" to adjust the brightness

### 2. Fan Control
Users can control the fan in each room:
- **On/Off Button** to toggle the fan state
- **Three Speed Buttons**: "Low", "Medium", "High" to adjust the speed

### 3. Room-Based Control
The system will control lights in three rooms:
- **Living Room**
- **Bedroom**
- **Kitchen**

Each room will have its own set of controls (On/Off and Brightness levels).

## GUI Design (JavaFX)
Use **JavaFX** to create a basic graphical interface that displays:
- An **On/Off** button for each room
- **Three buttons for brightness control** ("Low", "Medium", "High")
- A **label showing the current state** (On/Off) and brightness level for each light

## Constraints

### 1. Light States
- If a light is **off**, the brightness buttons should not work.
- Once the light is **on**, users can choose between three brightness levels.

### 2. Initial States
- Each light should start in the **"Off" state** with no brightness set.

## Sample GUI Layout
For each room (**Living Room, Bedroom, Kitchen**):
- **On/Off Button**
- **Brightness Buttons** (Radio Buttons):
  - "Low"
  - "Medium"
  - "High"
- **State Label**: Displays the current state, like "Light is On, Brightness: Medium" or "Light is Off."
