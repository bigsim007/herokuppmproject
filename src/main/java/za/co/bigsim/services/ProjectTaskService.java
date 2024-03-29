package za.co.bigsim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.bigsim.doa.BacklogRepository;
import za.co.bigsim.doa.ProjectRepository;
import za.co.bigsim.doa.ProjectTaskRepository;
import za.co.bigsim.domain.Backlog;
import za.co.bigsim.domain.Project;
import za.co.bigsim.domain.ProjectTask;
import za.co.bigsim.exceptions.ProjectNotFoundException;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectService projectService;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
		
        //Exceptions: Project not found

        //PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
        
        if(backlog == null)
        	throw new ProjectNotFoundException("Project not found");
        
        //set the bl to pt
        projectTask.setBacklog(backlog);
        //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
        Integer BacklogSequence = backlog.getPTSequence();
        // Update the BL SEQUENCE
        BacklogSequence++;
        
        backlog.setPTSequence(BacklogSequence);

        //Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority when priority null
        if(projectTask.getPriority()==null || projectTask.getPriority()==0){
            projectTask.setPriority(3);
        }
        
        //INITIAL status when status is null
        if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findBacklogByBackId(String id, String username) {
	
		projectService.findProjectByIdentifier(id, username);
			
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}

	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username) {
		
		projectService.findProjectByIdentifier(backlog_id, username);
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project task: '" +backlog_id+ "' not found");
		}
		
		if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project task: '" +pt_id+ "' does not exist in project: '" +backlog_id);	
		}
				
		return projectTask;		
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username) {
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id,username);
		projectTask = updatedTask;
		return projectTaskRepository.save(projectTask);
		
	}
   
    public void deletePTByProjectSequence(String backlog_id, String pt_id, String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id,username);
        projectTaskRepository.delete(projectTask);
    }
}
