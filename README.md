# Java Core
## task01:
### Практическое задание - StringBuilder

Изучите внутреннюю реализацию класса StringBuilder и напишите свою с добавлением дополнительного метода - undo().  

Прежде чем приступать - прочитайте про паттерн state и примените его в своей реализации.

# task02:
## Практическое задание - LocalDateTime

На вход вам дается время в виде класса LocalDateTime, орисуйте его в виде:

год:месяц:день##:час:минут:секунды:милисекунды

Используйте для этого аннотации jackson в спринге. И не забудьте про локаль.

# Java Collection
## task01:
### Практическое задание - Collection - фильтрация

Напишите метод filter, который принимает на вход массив любого типа, вторым арументом метод должен принимать клас, реализующий интерфейс Filter, в котором один метод - Object apply(Object o).

Метод должен быть реализован так чтобы возращать новый масив, к каждому элементу которого была применена функция apply
