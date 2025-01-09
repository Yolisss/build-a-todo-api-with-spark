'use strict';

angular.module('todoListApp')
.controller('mainCtrl', function($scope, Todo){
  
  $scope.todos = Todo.query();
  
  $scope.addTodo = function() {
    var todo = new Todo();
    todo.name = 'New task!'
    todo.completed = false;

    todo.$save(function(saveTodo){
    //on successful save, add the new to-do to the list
        $scope.todos.unshift(todo);
    }, function(error){
        console.error("Failed to save the todo:", error);
    })
  };
  
})