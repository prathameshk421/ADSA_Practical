document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('codes-container');
    const filterBtns = document.querySelectorAll('.filter-btn');
    const complexityTableBody = document.getElementById('complexity-table-body');

    // Dynamic color highlighting for complexity
    function getComplexityColor(complexityStr) {
        const text = complexityStr.toLowerCase();
        if (text.includes('o(1)') || text.includes('o(log n)') || text.includes('o(h)')) {
            return '#34d399'; // Emerald / Green
        } else if (text.includes('v + e') || text.includes('n + m')) {
            return '#60a5fa'; // Blue
        } else if (text.includes('e log v') || text.includes('e log e')) {
            return '#fbbf24'; // Amber / Yellow
        } else if (text.includes('n^2') || text.includes('v^2')) {
            return '#f87171'; // Red
        }
        return '#a78bfa'; // Purple (Fallback)
    }

    function renderComplexities() {
        if (!complexityTableBody) return;
        complexityTableBody.innerHTML = '';
        codesData.forEach(item => {
            const tr = document.createElement('tr');
            const badgeColor = getComplexityColor(item.complexity);
            tr.innerHTML = `
                <td style="font-weight: 500;">${item.title}</td>
                <td>
                    <div class="complexity-badge" style="color: ${badgeColor}; background: ${badgeColor}1a; border-color: ${badgeColor}33;">
                        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" style="margin-right: 6px;"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>
                        ${item.complexity}
                    </div>
                </td>
            `;
            complexityTableBody.appendChild(tr);
        });
    }

    // Function to render codes
    function renderCodes(filter) {
        container.innerHTML = '';
        
        const filteredData = filter === 'all' 
            ? codesData 
            : codesData.filter(item => item.assignment === filter);

        if(filteredData.length === 0) {
            container.innerHTML = '<p style="text-align:center; color: var(--text-secondary);">No practice codes found for this assignment.</p>';
            return;
        }

        filteredData.forEach((item, index) => {
            const card = document.createElement('div');
            card.className = 'code-card';
            card.style.animationDelay = `${index * 0.1}s`;

            const cleanCode = item.code.replace(/</g, "&lt;").replace(/>/g, "&gt;");

            // Complexity color function is now defined above
            
            const badgeColor = getComplexityColor(item.complexity);

            card.innerHTML = `
                <div class="card-header">
                    <h2 class="card-title">${item.title}</h2>
                    <div class="complexity-badge" style="color: ${badgeColor}; background: ${badgeColor}1a; border-color: ${badgeColor}33;">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" style="margin-right: 6px;"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>
                        ${item.complexity}
                    </div>
                </div>
                <div class="code-container">
                    <div class="code-header">
                        <span class="language-label">Java</span>
                        <button class="copy-btn" onclick="copyCode(this)">Copy Code</button>
                    </div>
                    <pre><code class="language-java">${cleanCode}</code></pre>
                </div>
            `;
            container.appendChild(card);
        });

        // Re-highlight syntax
        document.querySelectorAll('pre code').forEach((el) => {
            Prism.highlightElement(el);
        });
    }

    // Initial render
    renderCodes('all');
    renderComplexities();

    // Filter functionality
    filterBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            // Update active state
            filterBtns.forEach(b => b.classList.remove('active'));
            e.target.classList.add('active');

            // Filter codes
            const filter = e.target.getAttribute('data-filter');
            renderCodes(filter);
        });
    });
});

// Copy to clipboard functionality
window.copyCode = function(button) {
    const codeBlock = button.parentElement.nextElementSibling.querySelector('code');
    const textArea = document.createElement('textarea');
    textArea.value = codeBlock.innerText;
    document.body.appendChild(textArea);
    textArea.select();
    
    try {
        document.execCommand('copy');
        const originalText = button.innerText;
        button.innerText = 'Copied!';
        button.style.color = '#34d399';
        button.style.borderColor = '#34d399';
        
        setTimeout(() => {
            button.innerText = originalText;
            button.style.color = '';
            button.style.borderColor = '';
        }, 2000);
    } catch (err) {
        console.error('Failed to copy text', err);
    }
    
    document.body.removeChild(textArea);
};
