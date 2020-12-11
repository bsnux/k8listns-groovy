@Grab('io.kubernetes:client-java:10.0.0')

import io.kubernetes.client.openapi.Configuration
import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.util.KubeConfig
import io.kubernetes.client.util.ClientBuilder
import java.io.FileReader

def kubeConfPath = "${System.getenv("HOME")}/.kube/config"
def client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfPath))).build()
Configuration.setDefaultApiClient(client)

def api = new CoreV1Api()
def nsList = api.listNamespace(null, null, null, null, null, null, null, null, null)

nsList.getItems().each {
    println it.getMetadata().getName()
}