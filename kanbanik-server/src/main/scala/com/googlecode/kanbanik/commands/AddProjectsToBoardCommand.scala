package com.googlecode.kanbanik.commands

import com.googlecode.kanbanik.builders.ProjectBuilder
import com.googlecode.kanbanik.dtos.{PermissionType, ProjectDto, ErrorDto, ProjectWithBoardDto}
import com.googlecode.kanbanik.model.{Board, Project, User}
import com.googlecode.kanbanik.security._

class AddProjectsToBoardCommand extends BaseProjectsOnBoardCommand {

  private val builder = new ProjectBuilder()

  override def executeSpecific(board: Board, project: Project, user: User): Either[ProjectWithBoardDto, ErrorDto] = {

    val toStore = {
      if (project.boards.isDefined) {
        project.copy(boards = Some(board :: project.boards.get))
      } else {
        project.copy(boards = Some(List(board)))
      }
    }

    Left(ProjectWithBoardDto(builder.buildDto(toStore.store(user)), board.id.get.toString))
  }

}