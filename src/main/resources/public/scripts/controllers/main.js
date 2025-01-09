'use strict';

angular.module('todoListApp')
.controller('mainCtrl', function($scope, Todo){
  
  $scope.todos = Todo.query();
  
  $scope.addTodo = function() {
    if (!$scope.newTodoName || $scope.newTodoName.trim() === '') {
      alert("Task name cannot be empty!");
      return;
    }

    var todo = new Todo();
    todo.name = $scope.newTodoName ? $scope.newTodoName.trim() : 'New task!';

    todo.completed = false;

    todo.$save(function(savedTodo) {
      $scope.todos.unshift(savedTodo); // Add the saved todo to the list
      $scope.newTodoName = ''; // Clear the input box after saving
    }, function(error) {
      console.error("Failed to save the todo:", error);
    });
  };
})