# Text Quest (Servlet + JSP)

Учебный проект — текстовый детективный квест на Java Servlet + JSP.

Игрок проходит сценарий, выбирая варианты действий. Каждое решение ведёт к следующей сцене или финалу.

## Стек
- Java Servlet API
- JSP
- JSTL
- Jackson (JSON)
- HttpSession

## Суть работы

Игра представляет собой граф:

- Question — узел (сцена)
- variants — переходы к другим узлам
- Choice — кнопка выбора

## Поток

1. Пользователь вводит имя и начинает игру
2. Состояние сохраняется в HttpSession:
   - playerName
   - gameState
3. При каждом выборе:
   - POST /game с nextState
   - обновление gameState
   - redirect на GET /game
4. GET /game:
   - загрузка Question из JSON
   - формирование GameView
   - отображение в JSP

## Слои

- GameServlet — контроллер
- RequestHandler — работа с request/session
- GameService — бизнес-логика
- QuestionRepositoryImpl — загрузка JSON в память
- ConfigLoader — чтение JSON
- DTO (QuestionDTO, QuestConfig) — парсинг JSON
- GameView — модель для JSP
- Choice — варианты ответов

## Хранение данных

Вопросы хранятся в config.json и загружаются в память при старте приложения.

## Финалы

- 9 — успешное раскрытие дела
- 10 — провал расследования

## Особенности

- без базы данных (in-memory хранилище)
- session-based состояние
- JSON как источник сценария
- разделение DTO и доменной модели
- MVC архитектура

## Запуск

Развернуть на Tomcat и открыть:
`/game`
