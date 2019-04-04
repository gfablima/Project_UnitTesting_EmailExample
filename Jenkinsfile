def userdir = '/Users/glorialima'
def status = 'S'

node {
	stage('Build') {		
			echo 'Construindo...'
			try {
			  sh "sudo chown -R jenkins: ${WORKSPACE}"
			  deleteDir()			  
			  checkout scm
			  sh "sudo printenv > resultado"
			 }
			catch (e) {
			  status = 'F'
			  echo 'Falhou.'
			  throw e
			}
			finally {
		      if (status=='S'){ 
			    sh 'echo "Construção finalizada com sucesso"  >> resultado'
				stash includes: '**/resultado', name: 'res'
			   }
		      else{
		        sh 'echo "Construção finalizada com erro" >> resultado'
				archiveArtifacts artifacts: '**/resultado', fingerprint: true
			 }
		  }
		
	}
	stage('Test') {		
			echo 'Executando Testes Unitários e Testes de Integração ...'
			try {
			  sh "sudo docker run -v ${userdir}/devops/project2/srv/jenkins/workspace/${env.JOB_NAME}:/workspace -w /workspace maven:latest mvn clean install" 
			  echo 'Sucesso!'			
			  archiveArtifacts artifacts: '**/target/site/jacoco/index.html', fingerprint: true
			}
			catch (e) {
			  status = 'F'
			  echo 'Falhou.'
			  throw e
			}
		   finally {
		    unstash 'res'
		    if (status=='S')		     { 
		       sh 'echo "Testes finalizados com sucesso"  >> resultado'
			 }
		    else{
		       sh 'echo "Testes finalizados com erro"  >> resultado'
			}
			archiveArtifacts artifacts: '**/resultado', fingerprint: true
		}
	}
	stage('Deploy') {
	
		echo 'Deploying....'
		
		sshagent(['git']) {
                  sh "mvn -B -DskipTests clean deploy"
                  sh "git push origin " + env.BRANCH_NAME
                  sh "git push origin v${v}"
                }
		
	}
}
