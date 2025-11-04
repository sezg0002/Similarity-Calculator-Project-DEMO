# 🧮 Similarity Calculator Project

## 📘 Overview
The **Similarity Calculator Project** implements a mathematical similarity engine based on formulas applied to *cases*, *triplets*, and *intervals*.  
Developed in **Java**, the system is modular, featuring dedicated packages for **data collection**, **data processing**, and **object modeling**.

This project was completed as part of an **academic software engineering assignment**.

---

## 👤 Author
- **Harun SEZGIN**  
  Software & Data Engineer  
  [LinkedIn](https://www.linkedin.com/in/harun-sezgin-925a2924b/)  

---

## 📂 Project Structure

```
├── Classes/                 # Core Java classes representing main objects
│   ├── Cas.java
│   ├── Triplet.java
│   └── Intervalle.java
│
├── datacollection/          # Handles data extraction and initialization
│   └── Extraction.java
│
├── dataprocessing/          # Similarity calculation engine
│   └── CalculateurSim.java
│
└── README.md
```

---

## 🧩 Main Components

### 🧱 Cas (Case)
Represents a single case in the knowledge base.  
Each case has:
- A unique identifier
- A state (status)
- A list of associated *Triplets*

### 📊 Intervalle (Interval)
Represents a numerical range defined by **minimum** and **maximum** values.  
Used for comparing numerical parameters within triplets.

### 🔺 Triplet
Defines a structured unit composed of:
- Two parameters (**P1**, **P2**)
- One **Intervalle** object representing a range of acceptable values.

---

## 🔍 Data Extraction
The `Extraction` class (in the `datacollection` package) is responsible for loading case data from an external file.  
It initializes a **dictionary of cases**, which serves as input for similarity computations.

Example usage:
```java
Extraction extraction = new Extraction("cases.txt");
Map<String, Cas> cases = extraction.getCases();
```

---

## 🧮 Similarity Computation
The `CalculateurSim` class (in the `dataprocessing` package) performs the **mathematical similarity calculations** between cases.

It uses multiple mathematical formulas, including:
- **DEV** – Deviation-based similarity
- **POS** – Position-based comparison
- **IPOS** – Interval position formula
- **DT** – Distance transformation metric

Example test run:
```bash
javac dataprocessing/CalculateurSim.java
java dataprocessing.CalculateurSim
```

---

## ⚙️ Usage Instructions

1. Ensure that **Java** is installed and added to your system path.  
2. Compile and run the main similarity computation file:

```bash
javac dataprocessing/CalculateurSim.java
java dataprocessing.CalculateurSim
```

3. The console output will display computed similarity values between test cases.

---

## 🧠 Key Learning Outcomes
- Implementation of **object-oriented modeling** in Java  
- Understanding of **similarity measures and mathematical modeling**
- Practice with **data structure manipulation** and **algorithmic computation**
- Modular project design following **software engineering standards**

---

## 📜 License
This project is intended for **academic and educational use**.  
You may reuse it for learning or research purposes with proper author attribution.

---
