# Spell Database System 🧙‍♂️

A hybrid data structure implementation in Java that combines Hash Tables and AVL Trees for efficient spell management, built as part of a Data Structures course at Ben-Gurion University.

## Overview

This project implements a sophisticated spell database system using a combination of:
- **Double Hashing** for collision resolution in hash tables
- **AVL Trees** for balanced binary search by power level
- **Hash-AVL hybrid structure** that organizes spells by category and power level

## Data Structures Implemented

### Part 1: Double Hash Table
- **`DoubleHashTable`** — implements open addressing with double hashing
- **Custom hash functions** — `hash1` and `hash2` for collision resolution
- **Performance tracking** — counts steps taken for insertions and lookups

### Part 2: Hash-AVL Spell Table
- **`AVLTree`** — self-balancing BST sorted by spell power level
- **`HashAVLSpellTable`** — hash table where each bucket contains a linked list of AVL trees (one per category)
- **Efficient queries** — O(1) category lookup + O(log n) power level search
- **Top-K retrieval** — reverse in-order traversal for largest power levels

## Features

### Double Hash Table
- ✅ Insertion with collision resolution
- ✅ Lookup by spell name
- ✅ Performance metrics (step counting)

### Hash-AVL Spell Table
- 🔍 **Search by category, name, and power level**
- ➕ **Insert spells** organized by category and sorted by power
- 📊 **Count spells** in the entire database or by category
- 🏆 **Get top K spells** in a category (by power level)

## Class Structure

```
├── SpellSimple.java       # Basic spell (name + cast words)
├── Spell.java             # Full spell (name, category, power, words)
├── DoubleHashTable.java   # Hash table with double hashing
├── AVLTree.java           # Self-balancing BST (sorted by power level)
├── HashAVLSpellTable.java # Hybrid structure (hash + AVL)
├── MainPart1.java         # Demo for double hash table
├── MainPart2.java         # Demo for hash-AVL table
├── Tester.java            # Unit tests
└── Main.java              # Entry point
```

## How to Run

**Requirements:** Java 8 or later

### Compile
```bash
javac hw/*.java
```

### Run Part 1 (Double Hash Table)
```bash
java hw.MainPart1
```

### Run Part 2 (Hash-AVL Spell Table)
```bash
java hw.MainPart2
```

### Run Tests
```bash
java hw.Tester
```

## Example Usage

### Part 1: Double Hash Table
```java
DoubleHashTable table = new DoubleHashTable(7);
table.put(new SpellSimple("Abracadabra", "Avada Kedavra"));
table.put(new SpellSimple("Shazam", "24K Magic in the air"));

System.out.println(table.getCastWords("Shazam")); 
// Output: "24K Magic in the air"
```

### Part 2: Hash-AVL Spell Table
```java
HashAVLSpellTable table = new HashAVLSpellTable(10);
table.addSpell(new Spell("fireball", "fire", 10, "fireball!"));
table.addSpell(new Spell("flamethrower", "fire", 15, "foooooooo!"));
table.addSpell(new Spell("fireball II", "fire", 12, "fireball!!"));

List<Spell> top3 = table.getTopK("fire", 3);
// Returns: [flamethrower(15), fireball II(12), fireball(10)]
```

## Data Structure Concepts Demonstrated

### Double Hashing
- **Primary hash function:** `hash1(name) = (Σ char * 31) % capacity`
- **Secondary hash function:** `hash2(name) = 1 + (Σ char * 13) % (capacity - 2)`
- **Probing sequence:** `(hash1 + i * hash2) % capacity`

### AVL Tree
- **Self-balancing** with rotations (left, right, left-right, right-left)
- **Height tracking** at each node
- **Balance factor** = height(left) - height(right)
- **Insertion:** O(log n) with automatic rebalancing

### Hybrid Structure
- **Hash table** for O(1) category lookup
- **Linked list** per bucket for handling hash collisions between categories
- **AVL tree** per category for O(log n) power level operations
- **Overall complexity:** O(1 + log n) for category + power search

## Technologies

- Java
- Custom data structure implementations (no built-in Collections for core structures)
- Object-oriented design
- Generic programming

## Course

Data Structures — Ben-Gurion University of the Negev
