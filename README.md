# 🤖 AI Chatbot

A **Java-based Artificial Intelligence Chatbot** with a sleek **Swing GUI** interface.  
This chatbot can answer general questions, solve math problems, and interact intelligently with users. Perfect for learning, experimentation, or just having fun chatting with an AI!

---

## 🌟 Features

- **Interactive Chat GUI:** User-friendly interface for real-time conversation.
- **FAQ-Based Answers:** Responds to questions from a flexible `faq.txt` file.
- **Mathematical Calculations:** Handles arithmetic, exponents, square roots, logarithms, and constants like π, e, and phi.
- **Keyword Recognition:** Understands keywords even if the question is not exact.
- **Extensible:** Easily add new questions, answers, or mathematical operations.

---

## 🖥️ How to Run

1. Make sure **Java 17+** is installed.
2. Compile the project:
```bash
java -cp out com.ai.chatbot.ChatbotGUI
📝 Examples of Interaction

Mathematical Expressions:

2 * pi → 6.283185307179586

sqrt(16) → 4.0

log(100) → 2.0

phi ^ 2 → 2.6180339887

General Questions:

Hello → Hello! How can I help you today?

What is football? → Football is a popular sport played worldwide.

Help → You can ask me math questions like 2 * pi or general questions.

AI_Chatbot/
│
├─ src/
│   └─ com/ai/chatbot/
│       ├─ Chatbot.java      # Core logic
│       └─ ChatbotGUI.java   # GUI interface
│
├─ faq.txt                  # List of questions & answers
└─ README.md                # This file
