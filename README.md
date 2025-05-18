# take five

**take five** is a daily Android journaling app, built around simple rituals.

You **take five** minutes — in the morning and at night — to reflect, reset, and keep track of how things are going.

---

## 📸 sneak peek

<div style="text-align: center;">
  <img src="docs/home screen.png" width="180" />
  <img src="docs/morning prompt screen.png" width="180" />
  <img src="docs/evening prompt screen.png" width="180" />
  <img src="docs/archive screen.png" width="180" />
  <img src="docs/archive bottom sheet.png" width="180" />
</div>

---

### 🌱 why i built it

I wanted something calm, offline, and unintrusive — no insights, no pushy streaks, no cloud dependency with simple UI and solid architecture.

I also wanted it to feel like my real gratitude journal. One that lives only on your device — no syncing, no backup, no login. If you delete the app, it’s like tossing out a notebook. And once you write something, you can’t go back and edit it later — like you wrote it with a pen.

---

## 🧭 features

- 🌄 **morning prompts** – what you're grateful for & what would make today great
- 🌙 **evening prompts** – what went well & what you could've done better
- 🗓️ **archive** – calendar view to see which days you’ve journaled & read old entries
---

## 🧰 tech stack & architecture

100% Kotlin, Jetpack Compose, and modern Android architecture.

| Component            | Tools & Libraries                                                              |
|----------------------|---------------------------------------------------------------------------------|
| UI                   | Jetpack Compose (Material 3), Compose Navigation                               |
| UI + State Management | ViewModel, `UiState` data classes, `StateFlow`                                 |
| Data Layer           | Room (Entities, DAO, Database), Repository                       |
| Domain Layer         | UseCases                                                    |
| Dependency Injection | Hilt                                                                       |
| Async                | Kotlin Coroutines, Flow                                                        |
| Misc      | [`kizitonwose/Calendar`](https://github.com/kizitonwose/Calendar)              |> 🧠 I was intentionally experimenting with clear modern Android architecture layers (UI, domain, data) for maintainability.

 Most calls that touch the database go through a UseCase just for experimenting with. Repository calls the DAO directly (no DataSource).

---

<!--
## 🏁 getting started

```bash
git clone https://github.com/yourusername/take-five.git
cd take-five
./gradlew installDebug
```

Open in Android Studio and run it on your device or emulator.

---
-->

## 🛣️ roadmap

- [ ] Gentle notification reminders (optional)
- [ ] Custom day boundaries — set your “day end” time (e.g., 4 AM instead of midnight) to better fit late-night routines
- [ ] Better tablet + foldable UI

---

## 🤝 feedback

I built **take five** just for myself, but it’s open to be used by anybody. If you have ideas or want to contribute - feel free to reach out!
