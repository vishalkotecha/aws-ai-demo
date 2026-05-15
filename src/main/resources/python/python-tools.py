from datetime import datetime
import platform
from langchain.tools import tool
from langchain.agents import initialize_agent, AgentType
from langchain_core.prompts import ChatPromptTemplate
from langchain_aws import ChatBedrockConverse

@tool
def get_python_version():
    """Get Python version of the project"""
    return platform.python_version()


@tool
def get_fastapi_version():
    """Get FastAPI version"""
    return "FastAPI 0.115.0"


@tool
def get_current_date_time():
    """Get current date time"""
    return datetime.now().isoformat()


tools = [
    get_python_version,
    get_fastapi_version,
    get_current_date_time
]


llm = ChatBedrockConverse(
    model="amazon.nova-pro-v1:0",
    region_name="us-east-1",
    temperature=0
)

prompt = ChatPromptTemplate.from_messages([
    ("system", "You are a helpful assistant"),
    ("human", "{input}"),
    ("placeholder", "{agent_scratchpad}")
])

agent = create_tool_calling_agent(
    llm=llm,
    tools=tools
    prompt=prmot;
)

agent_executor = AgentExecutor(
    agent=agent,
    tools=tools,
    verbose=True
)

response = agent.invoke(
    "What Python version is used and what is the current time?"
)

print(response)