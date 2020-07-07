
def call(){
    /*
    Project details
    PROJECT_TITLE: Name of your parent project
    BRANCH: Name of the branch/environement (This is only needed if using tagging system)
    */
    env.PROJECT_TITLE = 'science-experiments-divya'
    env.BRANCH = 'dev'


    /* 
     == Jenkins credentials ==
    1. GOOGLE_APPLICATION_CREDENTIALS: Service account credentials of the gcr 
    2. GOOGLE_BUCKET_CREDENTIALS: Service account credentials of the bucket stated in ORIGIN_BUCKET
    */
    env.JENKINS_CRED = "${PROJECT_TITLE}"
    env.GOOGLE_APPLICATION_CREDENTIALS = credentials('gcp_service_account_key_sentient');
    env.GOOGLE_BUCKET_CREDENTIALS = credentials('gcp_service_account_key_bucket');


    /*
    == Disk options ==
      1. APP_NAME: Used for disk name
      2. CLUSTER_ZONE: Used for disk creation
      3. DISK_SIZE: in GB
      4. TYPE_OF_DISK : pd-ssd  or pd-hdd
    */
    env.APP_NAME = 'taxonomy'
    env.CLUSTER_ZONE = "asia-southeast1-a"
    env.DISK_SIZE = '10'  
    env.TYPE_OF_DISK = 'pd-ssd'


    /*
    == Image options ==
    1. IMAGE_TAG: Ensure this is the same as the one in your config.yaml
    */
    env.APP_TAG = "v0.1.0"
    env.IMAGE_TAG = "gcr.io/${PROJECT_TITLE}/${APP_NAME}:${APP_TAG}"


    /*
    == Bucket options ==
    1. ORIGIN_BUCKET: Where the models are stored (path)
    2. TARGET_BUCKET: Where the models are to be stored (path)
    */
    env.ORIGIN_BUCKET = "gs://sentient-science-models/nlp/taxonomy"
    env.TARGET_BUCKET = "gs://science-models-divya/"

    /*
    == Email log Options ==
    1. JOB_PATH: Location of log file in jenkins master 
        Format:
          - Pipeline without folder: jobs/<job_name>
          - Pipeline within folder: <file_name>/jobs/<job_name>
          - Multibranch pipeline within folder: <file_name>/jobs/<job_name>/branches
          - Multibranch pipeline and built with tags: <file_name>/jobs/<job_name>/branches/${RELEASE_NAME}.*
    2. RECIPIENT_EMAIL: Recipent of the log file 
    */
    env.TEMP = "${BRANCH_NAME}"
    env.RELEASE_NAME = "${TEMP.replace('.', '-')}"
    env.JOB_PATH = "microservice/jobs/masterbranch/branches/${RELEASE_NAME}.*"
    env.RECIPIENT_EMAIL = "charlotte@sentient.io"
}